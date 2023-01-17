package com.atguigu.gmall.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 功能描述：
 *  redis 的连接测试
 * @Author: mengzhengjin
 * @Date: 2022/12/19 22:54
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void test (){
       redisTemplate.opsForValue()
               .set("redis","hello，redis");
        String redis = redisTemplate.opsForValue().get("redis");
        System.out.println("测试成功："+redis);
    }
}
