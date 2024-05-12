package com.kgc.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private RedisTemplate redisTemplate;


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

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        stringRedisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    }
}
