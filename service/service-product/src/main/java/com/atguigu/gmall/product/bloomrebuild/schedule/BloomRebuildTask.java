package com.atguigu.gmall.product.bloomrebuild.schedule;

import com.atguigu.gmall.common.constant.SystemRedisConstant;
import com.atguigu.gmall.product.bloomrebuild.service.BloomDataQueryService;
import com.atguigu.gmall.product.bloomrebuild.service.RebuildBloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *  做定時任務：每周三晚上凌晨3點重建布隆
 * @Author: mengzhengjin
 * @Date: 2023/1/16 17:24
 */
@Service
public class BloomRebuildTask {
    @Autowired
    RebuildBloomFilterService rebuildBloomService;
    @Autowired
    BloomDataQueryService dataQueryService;

    @Scheduled(cron = "0 0 3 ? * 3")
    public void BloomFilterRebuildTask(){
    rebuildBloomService.bloomFilter(SystemRedisConstant.BLOOM_SKUID,dataQueryService)  ;
    }
}
