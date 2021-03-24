package com.weekthree.netty.action.gatewayserver;

import com.weekthree.netty.action.gatewayroute.RouteStrategy;
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

/**
 * 基于netty，作为一个网关服务端，接收客户端请求后，做过滤路由操作再将请求转发给后端服务
 */
public class NettyGatewayServer {


    private RouteStrategy routeStrategy;

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    private static Logger logger = LoggerFactory.getLogger(NettyGatewayServer.class);

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
        GatewayInitializer initializer = new GatewayInitializer();
        initializer.setRouteStrategy(routeStrategy);

        sb.group(bossEventLoop, workerEventLoop).channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO)).childHandler(initializer);
        try {
            Channel channel = sb.bind(GatewayConstant.gatewayPort).sync().channel();
            System.out.println("开启netty gateway ，监听地址和端口为 http://127.0.0.1:" + GatewayConstant.gatewayPort + '/');
//            logger.info("开启netty gateway ，监听地址和端口为 http://127.0.0.1:" + GatewayConstant.gatewayPort + '/');
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossEventLoop.shutdownGracefully();
            workerEventLoop.shutdownGracefully();
        }


    }


}
