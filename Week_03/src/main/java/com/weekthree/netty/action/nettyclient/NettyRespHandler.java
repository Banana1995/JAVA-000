package com.weekthree.netty.action.nettyclient;

import com.weekthree.netty.action.gatewayserver.GatewayConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.*;

import java.io.Serializable;
import java.util.Objects;

public class NettyRespHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext rebackCtx;
    private byte[] content;

    private DefaultFullHttpResponse response;

    private ChannelPromise promise;
//    public NettyRespHandler(ChannelHandlerContext rebackCtx) {
//        this.rebackCtx = rebackCtx;
//    }

    public ChannelPromise sendReq() {
        //客户端发送请求到netty网关
        String targetUri = "localhost:" + GatewayConstant.gatewayPort;
        System.out.println("发送请求" + targetUri);
        FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, targetUri);
        promise = rebackCtx.writeAndFlush(fullHttpRequest).channel().newPromise();
        return promise;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        FullHttpResponse response = (FullHttpResponse) msg;
//        String res = new String(response.content().array());
        //打印客户端接收到的消息回复


        if (msg instanceof String) {
            this.content = ((String) msg).getBytes();
        } else if (msg instanceof Serializable) {
//            this.content = SerialUtils.serial((Serializable) msg);
        } else if (msg instanceof DefaultFullHttpResponse) {
            this.response = (DefaultFullHttpResponse) msg;
        } else if (msg instanceof DefaultHttpResponse) {
            DefaultHttpResponse response = (DefaultHttpResponse) msg;
            if (response.decoderResult().isSuccess()) {
                this.content = new byte[response.headers().getInt(HttpHeaderNames.CONTENT_LENGTH)];
                this.response = new DefaultFullHttpResponse(response.protocolVersion(), response.status(),
                        Unpooled.buffer(0), response.headers(), response.headers());
            }
        } else if (Objects.nonNull(this.response) && msg instanceof DefaultLastHttpContent) {
            DefaultLastHttpContent httpContent = (DefaultLastHttpContent) msg;
            ByteBuf buf = httpContent.content();
            if (buf.hasArray()) {
                this.content = buf.array();
            } else {
                buf.readBytes(this.content);
            }
            ByteBuf byteBuf = Unpooled.buffer(this.content.length);
            byteBuf.writeBytes(this.content);
            byteBuf.retain();
            this.response = new DefaultFullHttpResponse(
                    this.response.protocolVersion(),
                    this.response.status(),
                    byteBuf,
                    this.response.headers(),
                    httpContent.trailingHeaders()
            );
            buf.release();
            this.promise.setSuccess();
            ctx.writeAndFlush(this.response);
        } else {
            throw new UnsupportedOperationException("不支持的对象 " + msg);
        }
        String mi = new String(this.response.content().array());
        System.out.println("client recive resp : " + mi);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        rebackCtx = ctx;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
//        consumer.processProxyRsp(null, origReq, consumer_ctx);
    }
}
