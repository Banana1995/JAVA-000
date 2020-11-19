package com.vincent.springboothomework.autoconfiguration;

import com.vincent.springboothomework.model.Frank;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PeopleConfiguration {

    @Bean
    Frank getFrank() {
        Frank frank = new Frank();
        frank.setName("frank by autoconfiguration");
        frank.setHabit("i like study");
        frank.setSkinColor("yellow");
        frank.setAge(25);
        return frank;
    }




}
