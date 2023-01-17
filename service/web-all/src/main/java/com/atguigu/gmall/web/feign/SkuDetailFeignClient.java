package com.atguigu.gmall.web.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：
 * 调用item微服务：获取商品的具体信息
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/25 22:28
 */
@RequestMapping("/api/inner/rpc/item")
@FeignClient("service-item")
public interface SkuDetailFeignClient {

    /**
     * 调用商品;product:获取相关数据接口
     *
     * @return
     */
    @GetMapping("/skuDetail/{skuId}")
    public Result<SkuDetailInfoTo> getSkuDetail(@PathVariable("skuId")Long skuId);
}
