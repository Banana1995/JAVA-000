package com.vincent.springboothomework;

import com.vincent.springboothomework.autoconfiguration.PeopleAutoConfiguration;
import com.vincent.springboothomework.jdbcpractice.UserHandle;
import com.vincent.springboothomework.model.Frank;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
//@EnableTransactionManagement
@SpringBootApplication
@EnableAsync
public class SpringboothomeworkApplication implements ApplicationContextAware , DisposableBean {
    private static ApplicationContext staticContext;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringboothomeworkApplication.class, args);
//        ConfigurableApplicationContext context = new SpringApplicationBuilder(PeopleAutoConfiguration.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//        Frank bean = context.getBean(Frank.class);
//        System.out.println(bean.toString());
//        UserHandle userHandle = staticContext.getBean(UserHandle.class);
//        userHandle.insert(bean);
//        System.exit(-1);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        staticContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(Thread.currentThread().getName() + "cccc");
    }
}
