package com.atguigu.gmall.product.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/13 16:17
 * data：get set
 * component：组件
 * configuration properties：读取yml文件中属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.minio")
public class MinioProperties {
    String endpoint;
    String ak;
    String sk;
    String bucketName;
}
