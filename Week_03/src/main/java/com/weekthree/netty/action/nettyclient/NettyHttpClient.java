package com.weekthree.netty.action.nettyclient;

import com.weekthree.netty.action.gatewayserver.GatewayConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

/**
 * 基于netty作为请求客户端，发送请求
 */
public class NettyHttpClient {

    private NettyRespHandler handler;

    public static void main(String[] args) {
        NettyHttpClient nettyHttpClient = new NettyHttpClient();
        nettyHttpClient.dohttpget();
    }

    public void dohttpget() {
        start();
    }

    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        try {
            EventLoopGroup workers = new NioEventLoopGroup();
            handler = new NettyRespHandler();
            bootstrap.group(workers).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new HttpResponseDecoder());
                            pipeline.addLast(new HttpRequestEncoder());
//                            pipeline.addLast(new HttpObjectAggregator(1024 * 10 * 1024));
                            pipeline.addLast(handler);
                        }
                    });
            Channel fu = bootstrap.connect("127.0.0.1", GatewayConstant.gatewayPort).sync().channel();
            ChannelPromise promise = handler.sendReq();
            try {
                promise.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                fu.close();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
