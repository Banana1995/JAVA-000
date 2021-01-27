package com.vincent.springboothomework.redisaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
public class LettuceConfig {

    @Bean
    public RedisTemplate<String, Serializable> getLettuceTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Serializable> res = new RedisTemplate<>();
        res.setConnectionFactory(factory);
        res.setKeySerializer(new StringRedisSerializer());
        res.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return res;
    }

}
