package com.atguigu.gmall.item.feign;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.to.CategoryView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/27 20:51
 */
@RequestMapping("/api/inner/rpc/product")
@FeignClient("service-product")
public interface SkuDetailFeign {


//    @GetMapping("/skuDetail/{skuId}")
// Result<SkuDetailInfoTo> getSkuDetail(@PathVariable("skuId")Long skuId);
    /**
     *  6:根据sku获取其他兄弟信息：方便跳转：
     *
     */
    @GetMapping("/skuDetail/brotherSkuSaleValue/{spuId}")
    Result<String> getBrotherSkuSaleValue(@PathVariable("spuId")Long spuId);

    /**
     *  5:获取销售属性名和值：saleAttrMapper.getSpuDate(info.getSpuId(), skuId)
     */
    @GetMapping("/skuDetail/skuSaleAndValue/{skuId}/{spuId}")
     Result<List<SpuSaleAttr>> getSkuSaleAndValue(@PathVariable("skuId")Long skuId,
                                                  @PathVariable("spuId")Long spuId);


    /**
     *  4:获取1010 实时的价格;
     */
    @GetMapping("/skuDetail/skuPrice/{skuId}")
     Result<BigDecimal> get1010Price(@PathVariable("skuId")Long skuId);


    /**
     *  3:根据skuId获取图片信息
     */
    @GetMapping("/skuDetail/skuImage/{skuId}")
     Result<List<SkuImage>> getSkuImage(@PathVariable("skuId")Long skuId);

    /**
     *  2:根据3级分类的id获取sku info信息
     */

    @GetMapping("/skuDetail/categoryInfo/{category3Id}")
     Result<CategoryView> getCategoryInfo(@PathVariable("category3Id")Long category3Id);


    /**
     *  1:获取sku的基本信息
     */
    @GetMapping("/skuDetail/skuInfo/{skuId}")
     Result<SkuInfo> getSkuInfo(@PathVariable("skuId")Long skuId);
}
