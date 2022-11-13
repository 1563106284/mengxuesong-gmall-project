package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.domain.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mengxueshong
* @description 针对表【spu_info(商品表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface SpuInfoService extends IService<SpuInfo> {
    /**
     * 1: 保存spu 的json 大数据
     * @param spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);
}
