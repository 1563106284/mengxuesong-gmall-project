package com.atguigu.gmall.item;

import com.atguigu.gmall.common.config.redssonconfig.AutoConfigurationRedission;


import com.atguigu.gmall.common.config.threadconfig.AutoThreadPoolClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 功能描述：
 *  抽取线程池思路：
 *  1：移植到公共模块
 *  2：修改配置注解
 *  3：thread pool configuration: @EnableConfigurationProperties
 *   把配置注入到当前模块
 *  4：app thread properties: 删除 @component
 *  5：定义自定义的注解 autoThreadPool
 * @Author: mengzhengjin
 * @Date: 2022/11/22 0:52
 */
@EnableAspectJAutoProxy
@AutoConfigurationRedission
@AutoThreadPoolClient
@EnableFeignClients
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }
}
