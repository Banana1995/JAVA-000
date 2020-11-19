package com.vincent.springaction.demointerface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrankConfig {

    @Bean
    public static Frank getPresident() {
        Frank res = new Frank();
        System.out.println("be invoked...");
        res.setAge(76);
        res.setHabit("dacing by @ bean");
        res.setName("Franklin by @ bean");
        res.setSkinColor("yellow by @ bean");
        return res;
    }

}
