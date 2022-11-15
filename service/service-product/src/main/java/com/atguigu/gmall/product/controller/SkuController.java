package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;

import com.atguigu.gmall.common.result.ResultCodeEnum;
import com.atguigu.gmall.product.domain.SkuInfo;
import com.atguigu.gmall.product.domain.SpuImage;
import com.atguigu.gmall.product.domain.SpuSaleAttr;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuImageService;


import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述：
 * sku后台管理接口
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/13 23:
 */
@Api(tags = "sku后台接口")
@RestController
@RequestMapping("/admin/product")
public class SkuController {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    /**
     *  6： sku json 数据的保存
     *  http://192.168.200.1/admin/product/saveSkuInfo
     */
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        List<SkuInfo> skuInfoList=skuInfoService.saveSkuInfo(skuInfo);
        return Result.ok(skuInfoList);
    }

    /**
     * 5：根据spuid 查询出销售属性名 和值
     * http://192.168.200.1/admin/product/spuSaleAttrList/24
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable("spuId") Long spuId) {
        List<SpuSaleAttr> list = spuSaleAttrService.spuSaleAttrList(spuId);
        return Result.ok(list);
    }

    /**
     * http://192.168.200.1/admin/product/spuImageList/24
     * 4: 添加sku功能中:获取spu图片接口
     */
    @GetMapping("/spuImageList/{spuId}")
    public Result byIdGetSpuImageList(@PathVariable("spuId") Long spuId) {
        QueryWrapper<SpuImage> queryWrapper = new QueryWrapper();
        queryWrapper.eq("spu_id", spuId);
        List<SpuImage> imageList = spuImageService.list(queryWrapper);
        return Result.ok(imageList);
    }

    /**
     * 3:sku的上架接口：
     * http://192.168.200.1/admin/product/onSale/45
     */
    @GetMapping("/onSale/{saleId}")
    public Result onSale(@PathVariable("saleId") Long saleId) {
        UpdateWrapper<SkuInfo> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", saleId).set("is_sale", 1);
        skuInfoService.update(updateWrapper);
        return Result.ok();
    }

    /**
     * 2:sku的下架接口：
     * http://192.168.200.1/admin/product/cancelSale/46
     */
    @GetMapping("/cancelSale/{saleId}")
    public Result cancelSale(@PathVariable("saleId") Long saleId) {
        UpdateWrapper<SkuInfo> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", saleId).set("is_sale", 0);
        skuInfoService.update(updateWrapper);
        return Result.ok();
    }

    /**
     * 1；sku分页接口
     *
     * @return http://192.168.200.1/admin/product/list/1/10
     */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result skuPage(@PathVariable("pageNum") Long pageNum,
                          @PathVariable("pageSize") Long pageSize) {
        Page<SkuInfo> page = new Page();
        Page<SkuInfo> skuInfoPage = skuInfoService.page(page);
        return Result.ok(skuInfoPage);
    }
}

