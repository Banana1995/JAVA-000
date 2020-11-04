package com.weekthree.netty.action.nettyclient;

import com.weekthree.netty.action.filter.HeadNameFilter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class GatewayInit extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        GatewayHandler gatewayHandler = new GatewayHandler();
        HeadNameFilter headNameFilter = new HeadNameFilter();
        gatewayHandler.setHeadNameFilter(headNameFilter);
        pipeline.addLast(gatewayHandler);
    }
}
