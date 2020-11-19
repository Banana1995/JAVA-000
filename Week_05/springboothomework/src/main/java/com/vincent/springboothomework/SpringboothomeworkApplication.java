package com.vincent.springboothomework;

import com.vincent.springboothomework.autoconfiguration.PeopleAutoConfiguration;
import com.vincent.springboothomework.model.Frank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringboothomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringboothomeworkApplication.class, args);
        ConfigurableApplicationContext context = new SpringApplicationBuilder(PeopleAutoConfiguration.class)
                .web(WebApplicationType.NONE)
                .run(args);
        Frank bean = context.getBean(Frank.class);
        System.out.println(bean.toString());
    }

}
