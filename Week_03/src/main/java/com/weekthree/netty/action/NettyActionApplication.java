package com.weekthree.netty.action;

import com.weekthree.netty.action.nettyclient.NettyGateway;

public class NettyActionApplication {



    public static void main(String[] args) {
        NettyGateway nettyGateway = new NettyGateway("127.0.0.1","8899");
        nettyGateway.run();
    }


}
