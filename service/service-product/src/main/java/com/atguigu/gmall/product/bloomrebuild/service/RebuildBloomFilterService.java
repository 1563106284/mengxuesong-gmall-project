package com.atguigu.gmall.product.bloomrebuild.service;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/16 15:54
 */
public interface RebuildBloomFilterService {
    /**
     *  1:重建布隆过滤器
     *  优化：使用模板模式优化重建布隆过滤器
     * @param bloomName
     * @return
     */
    Boolean bloomFilter(String bloomName,BloomDataQueryService dataQuery);
}
