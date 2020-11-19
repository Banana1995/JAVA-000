package com.vincent.springboothomework.autoconfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PeopleConfiguration.class)
public class PeopleAutoConfiguration {
}
