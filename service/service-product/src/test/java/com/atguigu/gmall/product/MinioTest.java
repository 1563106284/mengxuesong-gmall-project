package com.atguigu.gmall.product;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * minio 测试
 */
@SpringBootTest
public class MinioTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
        try {

            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.200.100:9000",
                    "admin", "admin123456");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("gmall");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("gamall");
            }

            // 使用putObject上传一个文件到存储桶中。
            // 创建文件上传流 ：
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\mengxueshong\\Desktop\\idea壁纸\\外星人壁纸");
            PutObjectOptions putObjectOptions = new PutObjectOptions(fileInputStream.available(),-1l);
            putObjectOptions.setContentType("image/png");
            minioClient.putObject("gmall","环境搭建.jpg", fileInputStream,putObjectOptions);
            System.out.println("上传成功");
        } catch(MinioException e) {
            System.err.println("发生错误: " + e);
        }
    }
}
