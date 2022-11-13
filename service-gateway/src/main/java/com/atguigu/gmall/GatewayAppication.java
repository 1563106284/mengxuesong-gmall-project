package com.atguigu.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 *  作用： 拦截 前端所有请求 让前端请求过来的路径 通过网关
 *  向下转发：不然前端会转到每个微服务 ，特别麻烦
 *
 *
 *  @SpringBootApplication
 * @EnableDiscov eryClient
 * @EnableCircuitBreaker 融合 = spring cloud application
 *
 *
 * 2：跨域的定义：
 *  浏览器发现 页面所在的地址和 它即将请求的地址不一致
 */

@SpringCloudApplication
public class GatewayAppication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAppication.class,args);
    }
}
