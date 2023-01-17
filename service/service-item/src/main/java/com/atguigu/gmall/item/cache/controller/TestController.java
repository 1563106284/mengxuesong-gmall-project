package com.atguigu.gmall.item.cache.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/17 12:37
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    SkuDetailService skuDetailService;
    @RequestMapping("/cache/{skuId}")
    public Result cacheTest(@PathVariable("skuId")Long skuId){
        SkuDetailInfoTo skuDetail = skuDetailService.getSkuDetail(skuId);
        return Result.ok(skuDetail);
    }
}
