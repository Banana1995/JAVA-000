package com.weekthree.netty.action;

import com.weekthree.netty.action.gatewayserver.NettyGatewayServer;

public class NettyActionApplication {



    public static void main(String[] args) {
        NettyGatewayServer nettyGatewayServer = new NettyGatewayServer();
        nettyGatewayServer.run();
    }



}
