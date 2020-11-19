package com.vincent.springaction.springbeanloadhomework;

import com.vincent.springaction.demointerface.Frank;
import com.vincent.springaction.demointerface.JackMa;
import com.vincent.springaction.demointerface.Trump;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VinSpringApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JackMa bean = applicationContext.getBean(JackMa.class);
        System.out.println(bean.toString());
        Trump trump = applicationContext.getBean(Trump.class);
        System.out.println(trump.toString());
        Frank frank = applicationContext.getBean(Frank.class);
        System.out.println(frank.toString());
    }


}
