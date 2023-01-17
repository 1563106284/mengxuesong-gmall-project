package com.atguigu.gmall.product.service;



import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import com.atguigu.gmall.product.to.ValueJsonTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
* @author mengxueshong
* @description 针对表【sku_info(库存单元表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface SkuInfoService extends IService<SkuInfo> {
    /**
     *  6: 获取当前sku所有id 保存到布隆过滤器
     * @return
     */
    List<Long> saveSkuId();
    /**
     *  5:获取当前sku其他兄弟销售属性数据
     * @param spuId
     * @return
     */
    String getBrotherSkuSaleValue(Long spuId);
    /**
     *  4优化1：获取sku基本信息
     * @param skuId
     * @return
     */
    Result<SkuInfo> getSkuInfo(Long skuId);
    /**
     *  3:查询sku的实时价格
     */
   BigDecimal getPrice(Long skuId);
    /**
     *  2:首页的详情数据
     * @param skuId
     * @return
     */
    SkuDetailInfoTo getSkuDetail(Long skuId);
    /**
     *  1：sku数据的大保存
     * @param skuInfo
     * @return
     */
    void saveSkuInfo(SkuInfo skuInfo);

}
