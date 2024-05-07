package com.kgc.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: 欧洋宏
 **/
@Component
public class ReplayUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Integer maxRandom = 100; // 最大储存的随机数

    private Integer minRandom = 20; // 最小储存的随机数


    private final List<String> randomList = new ArrayList<>(); // 待使用的随机数


    /**
     * 将随机数加载至maxRandom的数量
     */
    public void createUUIDToRedis() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        for (int i = 0; i < maxRandom - randomList.size(); i++) {
            String random = UUID.randomUUID().toString();
            randomList.add(random);
            String md5String = Md5Util.getMD5String(random);
            operations.set(md5String, random);
        }
    }

    /**
     * 创建单个随机数
     */
    public void createOneUUIDToRedis() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String random = UUID.randomUUID().toString();
        operations.set(random, random);
        randomList.add(random);
    }

    /**
     * 检查随机数
     *
     * @param random 需要检查的随机数
     * @return 返回随机数
     */
    public String checkRandom(String random) {
        String redisRandom = stringRedisTemplate.opsForValue().get(random);
        if (randomList.size() <= minRandom) {
            createUUIDToRedis();
        }
        return redisRandom;
    }

    /**
     * 获取随机数
     *
     * @return 返回随机数
     */
    public String getRandom() {
        int index = randomList.size() - 1;
        String random = randomList.get(index);
        randomList.remove(index);
        return random;
    }

    /**
     * 删除随机数
     *
     * @param random 需要删除的随机数
     */
    public void removeRandom(String random) {
        stringRedisTemplate.delete(random);
        createOneUUIDToRedis();
    }


}
