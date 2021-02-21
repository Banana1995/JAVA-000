package com.vincent.springboothomework.proxy;

public class NIO  implements Car{

    @Override
    public String getName() {
        return "NIO";
    }

    @Override
    public void driveTo() {
        System.out.println("new power car, called nio");
    }
}
