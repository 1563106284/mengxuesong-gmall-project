package com.atguigu.gmall.product.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 功能描述：
 *     封装 jackJson：将数据json格式之间转化
 * @Author: mengzhengjin
 * @Date: 2022/12/4 23:50
 */
public class JsonUtil {
    private static ObjectMapper objectMapper= new ObjectMapper();
    public static String toStr(Object object) {
        try {
            String value = objectMapper.writeValueAsString(object);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
