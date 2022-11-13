package com.atguigu.gmall.product.config.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/13 16:15
 */

/**
 * 模仿 springboot 读取redis 抽取minio
 * 1：创建minio的配置类
 * 2：创建minio的properties：
 * @Configuration :容器中的组件
 */
@Configuration
public class MinioConfiguration {
    @Autowired
    MinioProperties minioProperties;
    @Bean
    public MinioClient minioClient() throws Exception {
        // 1：创建minio的客户端
        MinioClient minioClient = new MinioClient(
                minioProperties.getEndpoint(),
                minioProperties.getAk(),
                minioProperties.getSk());
        return minioClient;
    }
}
