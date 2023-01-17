package com.atguigu.gmall.item.cache;

import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/15 17:37
 */

public interface CacheOpsService {
  <T>T getSkuData(String cacheKey, Class<T> clz);

  boolean tryLock(Object skuId);

  boolean bloomContains(Long skuId);

  void saveData(String cacheKey, Object object);

  void unlock(Object skuId);
}
