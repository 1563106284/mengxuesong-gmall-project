package com.atguigu.gmall.common.config.redssonconfig;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：
 * redissonClient 的配置类
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/5 18:37
 */
@AutoConfigureAfter(redissonAutoConfiguration.class)
@Configuration
public class redissonAutoConfiguration {
    // 1:redis相关配置数据 它都由
    @Autowired
    RedisProperties redisProperties;

    /**
     * 1：把redisson配置为redisson  client
     * 2：放入aop组件中
     *
     * @return
     */
    @Bean
    public RedissonClient redissonClientConfiguration() {
        Config config = new Config();
        int port = redisProperties.getPort();
        String password = redisProperties.getPassword();
        String host = redisProperties.getHost();
        config.useSingleServer()
                .setAddress("redis://"+host +":"+ port)
                .setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
