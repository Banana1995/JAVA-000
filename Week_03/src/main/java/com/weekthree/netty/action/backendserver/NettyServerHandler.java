package com.weekthree.netty.action.backendserver;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

public class NettyServerHandler extends SimpleChannelInboundHandler<HttpRequest> {

    private int backendPort;

    public NettyServerHandler(int backendPort) {
        this.backendPort = backendPort;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {

        String resp = "here is backend， server port is "+backendPort;

        FullHttpResponse response = new DefaultFullHttpResponse(httpRequest.protocolVersion(),
                HttpResponseStatus.OK, Unpooled.wrappedBuffer(resp.getBytes()));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf8")
                .setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

        channelHandlerContext.writeAndFlush(response);
    }
}
