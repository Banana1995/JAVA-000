package com.vincent.springboothomework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyContext {


    public static void main(String[] args) {

        NIO nio = new NIO();
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
//                return null;
                return method.invoke(nio, args);
            }
        };

        Car proxyCar = (Car) Proxy.newProxyInstance(NIO.class.getClassLoader(),nio.getClass().getInterfaces(), handler);
        proxyCar.driveTo();
        String name = proxyCar.getName();
        System.out.println(name);
    }

}
