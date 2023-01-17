package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import com.atguigu.gmall.web.feign.SkuDetailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：
 * 前端商品的详情页面
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/22 1:05
 */
@Controller
public class ItemController {
    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    @GetMapping("/{skuId}.html")
    public String itemPage(@PathVariable("skuId") Long skuId, Model model) {


        Result<SkuDetailInfoTo> skuRecord = skuDetailFeignClient.getSkuDetail(skuId);
        if (skuRecord.isOk()) {
            SkuDetailInfoTo skuDetail = skuRecord.getData();
            if (skuDetail ==null){
                return "item/404";
            }
            model.addAttribute("categoryView", skuDetail.getCategoryView());
            model.addAttribute("skuInfo", skuDetail.getSkuInfo());
            model.addAttribute("spuSaleAttrList", skuDetail.getSpuSaleAttrList());
            model.addAttribute("valuesSkuJson", skuDetail.getValuesSkuJson());
            model.addAttribute("price", skuDetail.getPrice());
        }
        return "item/index";
    }
}
