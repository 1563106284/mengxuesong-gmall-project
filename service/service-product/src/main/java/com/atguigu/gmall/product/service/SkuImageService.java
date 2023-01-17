package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【sku_image(库存单元图片表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface SkuImageService extends IService<SkuImage> {
    /**
     *  1：根据skuId获取图片信息
     * @param skuId
     * @return
     */
    List<SkuImage> getSkuImageInfo(Long skuId);
}
