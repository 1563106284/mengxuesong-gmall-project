package com.atguigu.gmall.item;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/12/7 12:09
 */
@SpringBootTest
public class ThreadTest {

    @Autowired
    ThreadPoolExecutor executor;
    @Test
   public void threadTest1(){
        for(int i=0;i <150;i++){
       executor.submit(()->{
//           System.out.println(Thread.currentThread().getName()+"执行任务");


               System.out.println("线程是"+Thread.currentThread().getName());
       });
        }


    }
}
