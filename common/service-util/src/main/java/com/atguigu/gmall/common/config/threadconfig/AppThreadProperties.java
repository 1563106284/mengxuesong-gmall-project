package com.atguigu.gmall.common.config.threadconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 功能描述：
 * 线程配置类：
 *
 * @Author: mengzhengjin
 * @Date: 2022/12/7 11:57
 */
@Data
//@Component
@ConfigurationProperties(prefix = "thread-pool.properties")
public class AppThreadProperties {
    /**
     * int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory,
     * RejectedExecutionHandler handler
     */

    Integer maximumPoolSize;
    Integer corePoolSize;
    Long keepAliveTime;
    Integer queueSize;

}
