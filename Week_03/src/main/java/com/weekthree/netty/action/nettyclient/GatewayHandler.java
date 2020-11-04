package com.weekthree.netty.action.nettyclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(GatewayHandler.class);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("channelRead流量接口请求开始");
        try {
            NettyOkHttpClient nettyOkHttpClient = new NettyOkHttpClient();
            FullHttpResponse response = nettyOkHttpClient.doHttpReq();
            ctx.write(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
            ReferenceCountUtil.release(msg);
        }
        logger.info("channelRead流量接口请求结束");
    }
}
