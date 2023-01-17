package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.DateUtil;
import com.atguigu.gmall.product.config.minio.MinioProperties;
import com.atguigu.gmall.product.service.FileUplocadService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/13 14:25
 */
@Service
public class FileUploadServiceImpl implements FileUplocadService {
    @Autowired
    MinioClient minioClient;
    @Autowired
    MinioProperties minioProperties;

    /**
     * 1:上传图片
     *
     * @param file
     * @return
     */

    @Override
    public String uploadFile(MultipartFile file) throws Exception {

        // 2:判断桶是否存在：
        if (minioClient.bucketExists(minioProperties.getBucketName())) {
            System.out.println("检测到java-gmall桶的存在");
        } else {
            minioClient.makeBucket(minioProperties.getBucketName());
        }
        //3: 配置相关的参数：
        /**
         *  String bucketName 桶名,
         *  String objectName 文件名
         *  , InputStream stream, 文件流
         *  PutObjectOptions options 参数
         */
        // 3.1:获取文件名 设置文件的唯一文件名：解决文件名字重复的问题
        String filename = UUID.randomUUID().toString().replace("_", "") + "_" + file.getOriginalFilename();
        // 3.2:获取流
        InputStream inputStream = file.getInputStream();
        // 3.3:获取上传类型
        String type = file.getContentType();
        // 3.5:设置日期
        String date = DateUtil.formatDate(new Date());
        // 3.3:配置上传参数：
        PutObjectOptions options = new PutObjectOptions(file.getSize(), -1L);
        options.setContentType(type);
        minioClient.putObject(
                minioProperties.getBucketName(),
                date + "/" + filename,//指定唯一的文件名
                inputStream,
                options);
        // 3.4:配置返回地址 地址+文件名
        String url = minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + date + "/" + filename;
        /**
         *  4:优化：
         *  1：通过设置 uuid.random 解决文件名重复问题
         *  1：通过日期来设置：知道什么时间上传的
         */
        return url;
    }
}
