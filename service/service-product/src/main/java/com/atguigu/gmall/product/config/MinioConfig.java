package com.atguigu.gmall.product.config;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
@Configuration
public class MinioConfig {

    @Bean
    public String UseMinio(MultipartFile file) throws Exception, Exception {
        //1:创建minio 客户端：
        MinioClient minioClient = new MinioClient("http://192.168.200.100:9000");
        // 2:判断桶是否存在：
        if (minioClient.bucketExists("gmall")) {
            minioClient.makeBucket("gmall");
        }
        //3:配置
        // 3.1: 文件的原始名
        String filename = file.getOriginalFilename();
        //  位置
        InputStream inputStream = file.getInputStream();
        // 文件类型
        String contentType = file.getContentType();


        // 配置选择参数：
        PutObjectOptions options = new PutObjectOptions(file.getSize(), -1L);
        options.setContentType(contentType);
        minioClient.putObject("gmall", filename, inputStream, options);

        //拼接url地址
        String urlPath= "http://192.168.200.100:9000/gmall/"+filename;
        return urlPath;
    }
}
