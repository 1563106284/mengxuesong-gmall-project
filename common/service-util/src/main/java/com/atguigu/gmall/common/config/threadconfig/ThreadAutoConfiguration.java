package com.atguigu.gmall.common.config.threadconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 功能描述：
 * 自定义线程池：还要进一步优化
 * 2：在进一步的优化：获取微服务的名
 *
 * @Author: mengzhengjin
 * @Date: 2022/12/5 7:31
 */
@EnableConfigurationProperties(AppThreadProperties.class)
@Configuration
public class ThreadAutoConfiguration {
    @Autowired
    AppThreadProperties threadProperties;
    // 通过value注解获取微服务的名字
    @Value("${spring.application.name}")
    String applicationName;


    @Bean
    public ThreadPoolExecutor coreThreadPoolExecutor() {
        /**
         *  int corePoolSize,
         *                               int maximumPoolSize,
         *                               long keepAliveTime,
         *                               TimeUnit unit,
         *                               BlockingQueue<Runnable> workQueue,
         *                               ThreadFactory threadFactory,
         *                               RejectedExecutionHandler handler
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                threadProperties.getCorePoolSize(),
                threadProperties.getMaximumPoolSize(),
                threadProperties.getKeepAliveTime(),
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(threadProperties.getQueueSize()),// 定义阻塞对列大小
                new ThreadFactory() {
                    int i = 0;// 记录线程号：id

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        // 定义名字
                        thread.setName(applicationName+"[核心线程" + i+++"]");
                        return thread;
                    }
                },// 定义拒绝策略：哪怕对列满了，也要用同步的方式执行任务
                new ThreadPoolExecutor.CallerRunsPolicy()

        );
        return threadPoolExecutor;
    }


}
