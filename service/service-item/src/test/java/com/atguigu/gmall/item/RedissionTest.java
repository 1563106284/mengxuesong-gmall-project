package com.atguigu.gmall.item;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/5 18:53
 */
@SpringBootTest
public class RedissionTest {
    @Autowired
    RedissonClient redissonClient;
    @Test
    void test01(){
        System.out.println("对象名"+redissonClient);
    }


}
