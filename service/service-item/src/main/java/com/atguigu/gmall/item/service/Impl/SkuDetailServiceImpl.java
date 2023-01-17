package com.atguigu.gmall.item.service.Impl;

import com.atguigu.gmall.common.constant.SystemRedisConstant;
import com.atguigu.gmall.item.cache.annotation.GmallCache;
import com.atguigu.gmall.item.cache.CacheOpsService;
import com.atguigu.gmall.item.feign.SkuDetailFeign;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 功能描述：
 * 商品详情服务
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/26 0:41
 */
@Slf4j
@Service
public class SkuDetailServiceImpl implements SkuDetailService {
    @Autowired
    SkuDetailFeign skuDetailFeign;

    @Autowired
    ThreadPoolExecutor executor;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    CacheOpsService cacheOpsService;

    private Map<Long, SkuDetailInfoTo> cacheItemMap = new ConcurrentHashMap();

    /**
     *  3:v3：结合aop+注解实现：
     *  缓存注解
     * @param skuId
     * @return
     */
    @GmallCache
    @Override
    public SkuDetailInfoTo getSkuDetail(Long skuId) {
        SkuDetailInfoTo info = getSkuDetailFromRPC(skuId);
        return info;
    }
    /**
     * 2:  压测结果：
     * 吞吐量：366.3个请求
     * 平均处理时间为 ：266/毫秒
     * 引入缓存：在进一步的优化 使用本地缓存
     * ---------------本地缓存的：
     * concurrent HashMap:作为缓存
     * 吞吐量为：9025个请求
     * 平均处理时间  1
     * <p>
     * ---------------使用分布式缓存 redis:
     * <p>
     * 吞吐量： 1153
     * 处理时间：79
     */

    public SkuDetailInfoTo getSkuDetailWitchCache(Long skuId) {
        // 1:定义缓存skuKey格式
        String cacheKey = SystemRedisConstant.SKU_INFO + skuId;
        // 2:查询缓存：
        SkuDetailInfoTo skuData = cacheOpsService.getSkuData(cacheKey, SkuDetailInfoTo.class);
        // 3：缓存没有问布隆：
        if (skuData == null) {
            // 3.1：询问bloom是否有当前sku
            boolean contain = cacheOpsService.bloomContains(skuId);
            // 3.2: 布隆 ：说没有直接返回
            if (!contain) {
                log.info("存在攻击风险：穿透中");
                return null;
            }
            // 3.3:布隆说可能有就可能有：缓存没有数据：需要回源数据 回源数据需要加锁
            boolean lock = cacheOpsService.tryLock(skuId);
            // 3.4:上锁成功：
            if (lock) {
                System.out.println("当前商品缓存没有：上锁中，准备回源");
                // 回源数据
                SkuDetailInfoTo skuDetailInfoTo = getSkuDetailFromRPC(skuId);
                // 保存到redis里面：
                cacheOpsService.saveData(cacheKey, skuDetailInfoTo);
                // 解锁：
                cacheOpsService.unlock(skuId);
                return skuDetailInfoTo;
            }
            // 3.5:在大并发情况下：没有抢到锁的：
            try {
                Thread.sleep(1000);
                return cacheOpsService.getSkuData(cacheKey, SkuDetailInfoTo.class);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // 缓存中有数据
        return skuData;
    }
//    @Override
//    public SkuDetailInfoTo getSkuDetail(Long skuId) {
//        // 1:查看缓存有没有：
//        String skuInfo = redisTemplate.opsForValue().get("sku:Info:" + skuId);
//        // 2:缓存没有 需要回源
//        if (StringUtils.isEmpty(skuInfo)) {
//            SkuDetailInfoTo skuDetailFromRPC = getSkuDetailFromRPC(skuId);
////            redisTemplate.opsForValue().set("skuInfo" + skuId, skuDetailFromRPC.toString());
//            redisTemplate.opsForValue().set("sku:Info:" + skuId, Jsons.toStr(skuDetailFromRPC));
//            return skuDetailFromRPC;
//        }
//        SkuDetailInfoTo skuDetailInfoTo = Jsons.toObj(skuInfo, SkuDetailInfoTo.class);
//        return skuDetailInfoTo;
//    }


    /**
     * 1:获取商品详情的数据: 使用本地缓存的方式
     *
     * @param skuId
     * @return
     */
//    @Override
//    public SkuDetailInfoTo getSkuDetail(Long skuId) {
//        // 1:查看缓存有不有
//        SkuDetailInfoTo info = cacheItemMap.get(skuId);
//        // 2：如果没有命中：回源
//        if (info == null) {
//            SkuDetailInfoTo skuDetailFromRPC = this.getSkuDetailFromRPC(skuId);
//            cacheItemMap.put(skuId, skuDetailFromRPC);
//            return skuDetailFromRPC;
//        }
//        return info;
//    }
    public SkuDetailInfoTo getSkuDetailFromRPC(Long skuId) {

//        Result<SkuDetailInfoTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId);
//        SkuDetailInfoTo data = skuDetail.getData();
        /**
         *  优化思想：1：不能让一个大接口去处理很多数据：拆分：拆分大接口
         *          2：拆成了6个小接口：（带来问题：多次调用：会有延迟问题）
         *          3：解决多次调用：串发问题：使用池化技术，自定义的线程池，做异步调用，解决问题
         *          线程池：作用：1:线程的资源复用 2：控制线程 3：+对列：
         */

        // 1:创建返回类：
        SkuDetailInfoTo skuDetailInfoTo = new SkuDetailInfoTo();
        // 1:获取sku基本信息
        CompletableFuture<SkuInfo> skuInfo = CompletableFuture.supplyAsync(() -> {
            SkuInfo info = skuDetailFeign.getSkuInfo(skuId).getData();
            skuDetailInfoTo.setSkuInfo(info);
            return info;
        }, executor);

        // 2:获取sku图片信息：
        CompletableFuture<Void> skuImage = skuInfo.thenAcceptAsync((info) -> {
            if (info != null) {
                skuDetailInfoTo.setSkuImages(skuDetailFeign.getSkuImage(skuId).getData());
            }
        }, executor);

        // 3:获取sku 1010 价格：
        CompletableFuture<Void> sku1010Price = skuInfo.thenAcceptAsync((info) -> {
            if (info != null) {

                skuDetailInfoTo.setPrice(skuDetailFeign.get1010Price(skuId).getData());
            }
        }, executor);
        // 4:获取sku的分类信息
        CompletableFuture<Void> skuCategory = skuInfo.thenAcceptAsync((info) -> {
            if (info != null) {

                skuDetailInfoTo.setCategoryView(skuDetailFeign.getCategoryInfo(info.getCategory3Id()).getData());
            }
        }, executor);

        // 5:获取商品spu信息
        CompletableFuture<Void> skuSpuInfo = skuInfo.thenAcceptAsync((info) -> {
            if (info != null) {

                skuDetailInfoTo.setSpuSaleAttrList(skuDetailFeign.getSkuSaleAndValue(skuId, info.getSpuId()).getData());
            }
        }, executor);

        // 6:获取sku的兄弟销售属性，让页面跳转作用：
        CompletableFuture<Void> skuBrother = skuInfo.thenAcceptAsync((info) -> {
                    if (info != null) {

                        skuDetailInfoTo.setValuesSkuJson(skuDetailFeign.getBrotherSkuSaleValue(info.getSpuId()).getData());
                    }
                }
                , executor);
        // 7:等待所有执行完 ，join 插入主线程之前执行
        CompletableFuture.allOf(skuImage, sku1010Price, skuCategory, skuSpuInfo, skuBrother).join();
        return skuDetailInfoTo;
    }

}
