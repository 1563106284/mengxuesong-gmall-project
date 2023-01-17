package com.atguigu.gmall.item.cache.annotation;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/16 20:16
 */
@Transactional
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GmallCache {
    // 动态获取annotation后的信息：cacheKey
    String cacheKey() default "";
}
