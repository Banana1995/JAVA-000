package com.weekthree.netty.action.backendserver;

import com.weekthree.netty.action.gatewayroute.RoundRobinStrategy;
import com.weekthree.netty.action.gatewayroute.RouteStrategy;
import com.weekthree.netty.action.gatewayserver.NettyGatewayServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.*;

public class NettyBackEndServer {

    private int backEndPort;

    public NettyBackEndServer(int backEndPort) {
        this.backEndPort = backEndPort;
    }

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory());

        RouteStrategy strategy = new RoundRobinStrategy();

        for (int i = 8085; i <= 8088; i++) {
            NettyBackEndServer backEndServer = new NettyBackEndServer(i);
            String backendUri = "http:localhost:" + i;
            strategy.regesterBackendServer(backendUri);
            service.execute(backEndServer::run);
        }
//        NettyBackEndServer backEndServer = new NettyBackEndServer(8082);
//        String backendUri = "http:localhost:" + "8082";
//        strategy.regesterBackendServer(backendUri);
//        backEndServer.run();
        NettyGatewayServer gatewayServer = new NettyGatewayServer();
        gatewayServer.setRouteStrategy(strategy);
        gatewayServer.run();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossEventGroup.shutdownGracefully();
            workerEventGroup.shutdownGracefully();
            System.out.println("backend server  port : " + backEndPort + "close");
        }
    }


}
