package com.atguigu.gmall.product.init;

import com.atguigu.gmall.common.constant.SystemRedisConstant;
import com.atguigu.gmall.product.service.SkuInfoService;
import jdk.nashorn.internal.ir.IfNode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/16 12:58
 */
@Slf4j
@Service
public class BloomSkuIdInit {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    RedissonClient redissonClient;

    /**
     *  1：production微服务启动的时候
     *   自动初始化：加载数据sku id 进入 布隆过滤器
     *   PostConstruct：当前成为服务组件之后：就会自动运行代码
     */
    @PostConstruct
    public void InitBloomSkuId(){
        log.info("正在初始化：布隆 加载数据中");
        // 1:查询所有的skuId
       List<Long> skuIds=skuInfoService.saveSkuId();
       // 2:导入redisson :拿到布隆
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(SystemRedisConstant.BLOOM_SKUID);
       // 2.1:判断布隆是否存在
        boolean exists = bloomFilter.isExists();
        if (!exists){
            // 布隆不存在 ：尝试初始化：1：插入多少数据 2：设置误判率
            bloomFilter.tryInit(5000000,0.00001);
        }
        // 3:添加数据
        skuIds.forEach((skuId)->{
            bloomFilter.add(skuId);
        });
        log.info("布初始化数据完成+{}"+skuIds.size());
    }
}
