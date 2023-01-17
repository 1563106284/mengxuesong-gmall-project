package com.atguigu.gmall.item.service;

import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;

/**
 * 功能描述：
 *      商品详情服务
 * @Author: mengzhengjin
 * @Date: 2022/11/26 0:40
 */
public interface SkuDetailService {
    /**
     *  1:获取sku详情信息
     * @param skuId
     * @return
     */
    SkuDetailInfoTo getSkuDetail(Long skuId);
}
