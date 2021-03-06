package com.weekthree.netty.action.gatewayserver;

import com.weekthree.netty.action.filter.HeadNameFilter;
import com.weekthree.netty.action.gatewayroute.RouteStrategy;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class GatewayInitializer extends ChannelInitializer {

    private RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    /**
     * 组织网关server端的 handler流水线
     *
     * @param channel
     * @throws Exception
     */
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        //将网关handler的处理逻辑加入流水线中
        GatewayHandler gatewayHandler = new GatewayHandler();
        HeadNameFilter headNameFilter = new HeadNameFilter();
        gatewayHandler.setHeadNameFilter(headNameFilter);
        gatewayHandler.setRouteStrategy(routeStrategy);
        pipeline.addLast(gatewayHandler);
    }
}
