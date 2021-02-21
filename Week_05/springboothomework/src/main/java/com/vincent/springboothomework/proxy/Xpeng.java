package com.vincent.springboothomework.proxy;

public class Xpeng implements Car {
    @Override
    public String getName() {
        return "Xpeng";
    }

    @Override
    public void driveTo() {
        System.out.println("xpeng drive experience is excellent");
    }
}
