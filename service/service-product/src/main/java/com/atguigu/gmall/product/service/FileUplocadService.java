package com.atguigu.gmall.product.service;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/13 14:25
 */
public interface FileUplocadService {
    /**
     * 1:上传图片
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file) throws InvalidPortException, InvalidEndpointException, Exception;
}
