package com.atguigu.gmall.common.config.threadconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 功能描述：
 *  线程池的自定义的注解
 * @Author: mengzhengjin
 * @Date: 2022/12/9 11:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ThreadAutoConfiguration.class)
public @interface AutoThreadPoolClient {
}
