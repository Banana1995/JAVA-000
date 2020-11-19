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
        return frank;
    }




}
