package com.weekthree.netty.action.nettyclient;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyGateway {

    private String proxyServer;
    private int proxyPort;

    public NettyGateway(String proxyServer, String proxyPort) {
        this.proxyServer = proxyServer;
        this.proxyPort = Integer.valueOf(proxyPort);
    }

    private static Logger logger = LoggerFactory.getLogger(NettyGateway.class);

    public void run() {
        BasicConfigurator.configure();
        EventLoopGroup bossEventLoop = new NioEventLoopGroup(1);
        EventLoopGroup workerEventLoop = new NioEventLoopGroup(16);

        ServerBootstrap sb = new ServerBootstrap();
        sb.option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(EpollChannelOption.SO_REUSEPORT, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        sb.group(bossEventLoop, workerEventLoop).channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new GatewayInit());

        try {
            Channel channel = sb.bind(proxyPort).sync().channel();
            logger.info("开启netty http服务器，bossEventLoop监听地址和端口为 http://127.0.0.1:" + proxyPort + '/');
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossEventLoop.shutdownGracefully();
            workerEventLoop.shutdownGracefully();
        }

    }


}
