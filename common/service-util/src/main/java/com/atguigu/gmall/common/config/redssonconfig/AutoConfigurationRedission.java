package com.atguigu.gmall.common.config.redssonconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/16 12:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(redissonAutoConfiguration.class)
public @interface AutoConfigurationRedission {
}
