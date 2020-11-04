package com.weekthree.netty.action.nettyclient;

import com.weekthree.netty.action.filter.HeadNameFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(GatewayHandler.class);

    private HeadNameFilter headNameFilter;

    public void setHeadNameFilter(HeadNameFilter headNameFilter) {
        this.headNameFilter = headNameFilter;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("channelRead流量接口请求开始");
        try {
            NettyOkHttpClient nettyOkHttpClient = new NettyOkHttpClient();
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            headNameFilter.filter(fullRequest, ctx);
            logger.info("request name is "+ fullRequest.headers().get("name"));
            FullHttpResponse response = nettyOkHttpClient.doHttpReq(fullRequest);
            ctx.write(response);
//            NettyHttpClient nettyHttpClient = new NettyHttpClient();
//            nettyHttpClient.dohttpget(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
            ctx.close();
            ReferenceCountUtil.release(msg);
        }
        logger.info("channelRead流量接口请求结束");
    }
}
