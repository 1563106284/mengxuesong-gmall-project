package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.domain.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【sku_info(库存单元表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface SkuInfoService extends IService<SkuInfo> {
    /**
     *  1：sku数据的大保存
     * @param skuInfo
     * @return
     */
    List<SkuInfo> saveSkuInfo(SkuInfo skuInfo);
}
