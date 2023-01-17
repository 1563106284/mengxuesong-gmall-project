package com.atguigu.gmall.item.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.to.CategoryView;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 *      总结：
 *      1:思路 逻辑  实现
 *      2：搭建框架： 从web-all -》返回数据，然后搭建feign调用接口
 *          创建item：写controller，对应编写出相对应的逻辑
 *          封装数据 ：组合一起，封装起来 2个类，一个返回的实体类，一个里面属性定义了一个类属性
 *          创建实现框架：service serviceImpl
 *      3:写清楚你需要哪些数据
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/25 22:21
 */
@Api(tags = "首页商品详情相关的数据被调用的接口")
@RestController
@RequestMapping("/api/inner/rpc/item")
public class SkuDetailApiController {

    @Autowired
    SkuDetailService  skuDetailService;


    /**
     * 调用商品;product:获取相关数据接口
     *
     * @return
     */
    @GetMapping("/skuDetail/{skuId}")
    public Result<SkuDetailInfoTo> getSkuDetail(@PathVariable("skuId")Long skuId) {


        SkuDetailInfoTo skuDetail =skuDetailService.getSkuDetail(skuId);

        return Result.ok(skuDetail);
    }


}
