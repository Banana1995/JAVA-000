package com.weekthree.netty.action.nettyclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

public class NettyRespHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext rebackCtx;

    public NettyRespHandler(ChannelHandlerContext rebackCtx) {
        this.rebackCtx = rebackCtx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;
        rebackCtx.write(response);
        rebackCtx.flush();
        rebackCtx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        FullHttpRequest fullHttpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, "localhost:8801");
        ctx.writeAndFlush(fullHttpRequest);
//        ctx.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
//        consumer.processProxyRsp(null, origReq, consumer_ctx);
    }
}
