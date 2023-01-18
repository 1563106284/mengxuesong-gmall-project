package com.atguigu.gmall.item.cache.aspect;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/18 19:31
 */
public class Hello {

    public List<String> list(){
        return Arrays.asList("1", "2", "3", "4");
    }
    public Hello findMethod(){
        return this;
    }
}
