package com.atguigu.gmall.product.controller.api;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.to.CategoryView;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import com.atguigu.gmall.product.to.ValueJsonTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 *  商品详情页面的内部调用接口
 * @Author: mengzhengjin
 * @Date: 2022/11/27 14:36
 */
@Api(tags = "商品详情的内部调用接口")
@RestController
@RequestMapping("/api/inner/rpc/product")
public class SkuDetailApiController {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    BaseCategory3Service baseCategory3Service;
    @Autowired
    SkuImageService skuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
//    @ApiOperation(value = "获取商品详情")
//    @GetMapping("/skuDetail/{skuId}")
//    public Result<SkuDetailInfoTo> getSkuDetail(@PathVariable("skuId")Long skuId){
//        SkuDetailInfoTo skuDetail  =skuInfoService.getSkuDetail(skuId);
//        return Result.ok(skuDetail);
//    }

    /**
     *  甩锅：接口优化：
     */
    /**
     *  6:根据sku获取其他兄弟信息：方便跳转：
     *
     */
    @GetMapping("/skuDetail/brotherSkuSaleValue/{spuId}")
    public Result<String>  getBrotherSkuSaleValue(  @PathVariable("spuId")Long spuId){
        String saleValue = skuInfoService.getBrotherSkuSaleValue(spuId);
        return Result.ok(saleValue);
    }
    /**
     *  5:获取销售属性名和值：saleAttrMapper.getSpuDate(info.getSpuId(), skuId)
     */
    @GetMapping("/skuDetail/skuSaleAndValue/{skuId}/{spuId}")
    public Result<List<SpuSaleAttr>> getSkuSaleAndValue(@PathVariable("skuId")Long skuId,
                                                        @PathVariable("spuId")Long spuId){
        List<SpuSaleAttr> spuSaleAttrList= spuSaleAttrService.getSpuDate(skuId,spuId);
        return Result.ok(spuSaleAttrList);
    }
    /**
     *  4:获取1010 实时的价格;
     */
    @GetMapping("/skuDetail/skuPrice/{skuId}")
    public Result<BigDecimal> get1010Price(@PathVariable("skuId")Long skuId){
        BigDecimal price = skuInfoService.getPrice(skuId);
        return Result.ok(price);
    }
    /**
     *  3:根据skuId获取图片信息
     */
    @GetMapping("/skuDetail/skuImage/{skuId}")
    public Result<List<SkuImage>> getSkuImage(@PathVariable("skuId")Long skuId) {
       List<SkuImage> imageList = skuImageService.getSkuImageInfo(skuId);
       return Result.ok(imageList);
    }
    /**
     *  2:根据3级分类的id获取sku info信息
     */

    @GetMapping("/skuDetail/categoryInfo/{category3Id}")
    public Result<CategoryView> getCategoryInfo(@PathVariable("category3Id")Long category3Id){
        CategoryView categoryView =baseCategory3Service.getCategoryInfo(category3Id);
        return Result.ok(categoryView);
    }
    /**
     *  1:获取sku的基本信息
     */
    @GetMapping("/skuDetail/skuInfo/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable("skuId")Long skuId){
        Result<SkuInfo> skuInfo=  skuInfoService.getSkuInfo(skuId);
        return skuInfo;
    }

}
