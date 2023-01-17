package com.atguigu.gmall.product.bloomrebuild.service.impl;

import com.atguigu.gmall.common.constant.SystemRedisConstant;
import com.atguigu.gmall.product.bloomrebuild.service.BloomDataQueryService;
import com.atguigu.gmall.product.bloomrebuild.service.RebuildBloomFilterService;
import com.atguigu.gmall.product.service.SkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/16 15:55
 */
@Slf4j
@Service
public class RebuildBloomFilterServiceImpl implements RebuildBloomFilterService {
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    SkuInfoService skuInfoService;
    /**
     *  1:重建布隆过滤器
     * @param bloomName
     * @return
     */
    @Override
    public Boolean bloomFilter(String bloomName, BloomDataQueryService dataQuery) {
        // 1:老布隆
        RBloomFilter<Object> oldBloomFilter = redissonClient.getBloomFilter(bloomName);
        // 2:新建布隆：
        RBloomFilter<Object> newBloomFilter = redissonClient.getBloomFilter(SystemRedisConstant.NEW_BLOOM_SKUID);
        // 3.1:让新布隆读取数据：
//        List<Long> skuIds = skuInfoService.saveSkuId();
        // 3.1 v2：让布隆读取数据：
        List list = dataQuery.bloomDataQuery();
        //3.2:初始化布隆
        newBloomFilter.tryInit(5000000,0.00001);
        // 3.3:给新布隆添加数据
        list.forEach((ids)->{
            newBloomFilter.add(ids);
        });
        // 3.4:新旧布隆交换 ： 并删除老布隆 中间布隆
        oldBloomFilter.rename("oldBloom");
        newBloomFilter.rename(bloomName);
        oldBloomFilter.deleteAsync();
        redissonClient.getBloomFilter("oldBloom").deleteAsync();
        // 3.5:判断新布隆：是否存在
        log.info("新布隆创建完毕：更新完数据 {}"+list.size());
        return     newBloomFilter.isExists();
    }
}
