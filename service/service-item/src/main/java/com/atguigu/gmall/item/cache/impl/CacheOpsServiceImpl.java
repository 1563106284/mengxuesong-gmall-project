package com.atguigu.gmall.item.cache.impl;

import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.item.cache.CacheOpsService;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import com.atguigu.gmall.product.util.JsonUtil;
import io.lettuce.core.RedisClient;
import jodd.time.TimeUtil;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.atguigu.gmall.common.constant.SystemRedisConstant;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/15 17:38
 */
@Service
public class CacheOpsServiceImpl implements CacheOpsService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    /**
     *  4:解锁
     * @param skuId
     */
    @Override
    public void unlock(Object skuId) {
        String lockKey=SystemRedisConstant.lOCK_SKU_DETAIL+skuId;
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }
    /**
     * 3:保存数据
     *
     * @param cacheKey
     * @param object
     */
    @Override
    public void saveData(String cacheKey, Object object) {
        // 1:回源的数据为空：保存为x ：设置保存时间 30分
        if (object == null) {
            redisTemplate.opsForValue().set(cacheKey, SystemRedisConstant.NULL_VALUE, SystemRedisConstant.NULL_VALUE_TTL, TimeUnit.MINUTES);
        } else {
            // 2:回源有数据，转换string类型后进行保存 时间为7天
            String info = Jsons.toStr(object);
            redisTemplate.opsForValue().set(cacheKey, info, SystemRedisConstant.SKUDETAIL_VALIE_TTL, TimeUnit.MINUTES);
        }
    }


    /**
     * 2:尝试加锁
     *
     * @param skuId
     * @return
     */
    @Override
    public boolean tryLock(Object skuId) {
        // 定义锁key
        String lockKey = SystemRedisConstant.lOCK_SKU_DETAIL + skuId;
        // 上锁：得到当前sku的锁
        RLock lock = redissonClient.getLock(lockKey);
        // 尝试加锁：
        boolean tryLock = lock.tryLock();
        return tryLock;
    }

    /**
     *  2:通过布隆过滤器判断当前sku id有没有
     * @param skuId
     * @return
     */
    @Override
    public boolean bloomContains(Long skuId) {
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(SystemRedisConstant.BLOOM_SKUID);
        return bloomFilter.contains(skuId);
    }


    //1:将缓存数据转换为定义的类型
    @Override
    public <T> T getSkuData(String cacheKey, Class<T> clz) {
        // 查询缓存：
        String skuInfo = redisTemplate.opsForValue().get(cacheKey);
        // 缓存数据：是x为空 直接返回
        if (SystemRedisConstant.NULL_VALUE.equals(skuInfo)) {
            return null;
        }
        // 不为空 转换数据
        T info = Jsons.toObj(skuInfo, clz);
        return info;
    }

}
