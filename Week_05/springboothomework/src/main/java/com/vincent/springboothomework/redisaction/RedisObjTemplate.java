package com.vincent.springboothomework.redisaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisObjTemplate {
    @Autowired
    private RedisTemplate<String, Serializable> objRedisTemplate ;

    public void getObj(){
        Serializable vincent = objRedisTemplate.opsForValue().get("vincent");
    }

}
