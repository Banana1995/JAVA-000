package com.weekthree.netty.action.backendserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.apache.log4j.BasicConfigurator;

public class NettyBackEndServer {

    private int backEndPort;

    public NettyBackEndServer(int backEndPort) {
        this.backEndPort = backEndPort;
    }

    public static void main(String[] args) {
        NettyBackEndServer backEndServer = new NettyBackEndServer(8802);
        backEndServer.run();
    }

    public void run() {
        BasicConfigurator.configure();
        EventLoopGroup bossEventGroup = new NioEventLoopGroup();
        EventLoopGroup workerEventGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.group(bossEventGroup, workerEventGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new HttpServerCodec())
                                    .addLast(new HttpObjectAggregator(1024 * 1024))
                                    .addLast(new NettyServerHandler(backEndPort));
                        }
                    });
            Channel channel = sbs.bind(backEndPort).sync().channel();
            System.out.println("backend server start port at " + backEndPort);
            channel.closeFuture().sync();
            System.out.println("backend server start over !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossEventGroup.shutdownGracefully();
            workerEventGroup.shutdownGracefully();
        }
    }


}
