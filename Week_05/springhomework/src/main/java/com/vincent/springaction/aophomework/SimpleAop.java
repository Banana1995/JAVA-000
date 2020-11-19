package com.vincent.springaction.aophomework;

import com.vincent.springaction.demointerface.JackMa;
import com.vincent.springaction.demointerface.People;
import com.vincent.springaction.demointerface.Trump;

public class SimpleAop {


    public static void main(String[] args) {
        JackMa jackMa = new JackMa();
        jackMa.setAge(54);
        jackMa.setName("jack ma");
        jackMa.setHabit("bragging");
        jackMa.setSkinColor("yellow");
        proxy(jackMa);
        System.out.println("meeting middle rest...");
        Trump trump = new Trump();
        trump.setAge(54);
        trump.setName("donald trump");
        trump.setHabit("business");
        trump.setSkinColor("white");
        proxy(trump);
    }

    private static void proxy(People people) {
        SimpleProxy simpleProxy = new SimpleProxy();
        simpleProxy.setPeople(people);
        System.out.println("before proxy,class name is :" + people.getClass().toString());
        People proxyInstance = (People) simpleProxy.getProxyInstance();
        System.out.println("after proxy,class name is :" + proxyInstance.getClass().toString());
        String name = proxyInstance.getName();
        System.out.println("name: " + name);
        String skinColor = proxyInstance.getSkinColor();
        System.out.println("skin color: " + skinColor);
        String greet = proxyInstance.shakeHands("vincent");
        System.out.println("they shake hands , and " + name + " say :" + greet);
    }

}
