package com.kgc.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 22:30
 * Redis工具类
 **/
@Component
public class RedisUtil implements ApplicationContextAware {
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;


    public  void setKey(String key, String value, int minutes) {
        stringRedisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
    }

    public  String getValueByKey(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }

    public  boolean removeKey(String key) {
        Boolean deleteFalg = stringRedisTemplate.delete(key);
        return deleteFalg;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        stringRedisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    }
}