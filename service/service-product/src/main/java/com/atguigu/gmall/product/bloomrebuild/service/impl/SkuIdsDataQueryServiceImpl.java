package com.atguigu.gmall.product.bloomrebuild.service.impl;

import com.atguigu.gmall.product.bloomrebuild.service.BloomDataQueryService;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *  使用模板模式：对重建需要的数据做了进一步的优化
 * @Author: mengzhengjin
 * @Date: 2023/1/16 16:51
 */
@Service
public class SkuIdsDataQueryServiceImpl implements BloomDataQueryService {
    @Autowired
    SkuInfoService skuInfoService;
    @Override
    public List bloomDataQuery() {
        return  skuInfoService.saveSkuId();
    }
}
