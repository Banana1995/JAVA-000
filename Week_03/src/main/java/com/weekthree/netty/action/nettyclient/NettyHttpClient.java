package com.weekthree.netty.action.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

import java.io.IOException;

public class NettyHttpClient {

    public void dohttpget(ChannelHandlerContext ctx)  {
        this.run(ctx);
    }

    public void run(ChannelHandlerContext ctx) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            EventLoopGroup workers = new NioEventLoopGroup();
            bootstrap.group(workers).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new HttpClientCodec());
                            pipeline.addLast(new HttpObjectAggregator(1024 * 10 * 1024));
                            pipeline.addLast(new NettyRespHandler(ctx));
                        }
                    });
            bootstrap.connect("127.0.0.1", 8801).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
