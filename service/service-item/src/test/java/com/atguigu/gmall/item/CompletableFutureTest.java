package com.atguigu.gmall.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能描述：
 * 知识复习：
 * 异步5种方式：
 * new thread
 * runnable
 * callable
 * threadPool executor
 * completable future
 *
 * @Author: mengzhengjin
 * @Date: 2022/12/9 18:15
 */
@SpringBootTest
public class CompletableFutureTest {
    /**
     * 异步编排：
     * 复习回顾：
     * 1：then run:不接受上一步的参数，执行，没有返回值
     * 2： then accept:接受上一步的参数，没有返回值
     * 3： then apply:接受上一步参数，有返回值
     * 4: .get得到上一步的值，会阻塞等待5
     * 5: 执行任务 run async：异步执行当前任务，无返回值
     * 6：supply  async：执行异步，有有返回值
     * 7：all of:收集完当前执行的几个任务，返回一个总future
     * 8：when complete：( u,t):t如果不为null就是有异常，可以做日志输出
     */
    @Autowired
    ThreadPoolExecutor executor;

    @Test
    public void st1() {
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "结果是：" + 1 + 1);
//        }, executor);
//
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(
//                () -> {
//                    System.out.println("执行返回值异步任务");
//                    return 2;
//                }
//                , executor);
//
//        future1.thenApplyAsync((r)->{
//            System.out.println("异步的结果"+r+1);
//            return r+1;
//        }, executor);

        /**
         *  异步编排：链式调用
         */
        CompletableFuture.supplyAsync(()->{
            return 1+1;
        },executor).thenApplyAsync((r)->{
            return r+1;
        },executor).thenAccept((r)->{
            System.out.println("执行完毕，保存到了数据库");
        }).whenComplete(
                (u,t)->{
                    if (t !=null){
                        System.out.println("有异常");
                    }
                }
        );
    }
}


