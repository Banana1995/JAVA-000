package com.vincent.springaction.aophomework;

import com.vincent.springaction.demointerface.People;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleProxy {

    private People people;

    public void setPeople(People people) {
        this.people = people;
    }

    public Object getProxyInstance() {
        Object proxyObj = Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("step begin..");
                Object obj = method.invoke(people, args);
                System.out.println("step over...");
                return obj;
            }
        });
        return proxyObj;
    }


}
