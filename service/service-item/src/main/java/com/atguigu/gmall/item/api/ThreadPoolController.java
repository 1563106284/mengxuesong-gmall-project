package com.atguigu.gmall.item.api;

import com.atguigu.gmall.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/12/8 10:52
 */
@RestController
@RequestMapping("/thread")
public class ThreadPoolController {
    @Autowired
    ThreadPoolExecutor executor;

    /**
     *  模拟关闭线程池的：做法
     * @return
     */
    @RequestMapping("/test")
    public Result method(){

        executor.shutdown();

        return Result.ok();
    }

}
