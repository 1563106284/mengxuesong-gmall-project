package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.to.ValueJsonTo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
* @author mengxueshong
* @description 针对表【sku_info(库存单元表)】的数据库操作Mapper
* @createDate 2022-11-06 21:15:10
* @Entity com.atguigu.gmall.product.domain.SkuInfo
*/
@Mapper
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {
    /**
     *  2:查询所有skuId
     *  作用：需要保存到布隆过滤器里面
     * @return
     */
    List<Long> selectSkuId();
    /**
     *  1:获取商品的实时价格
     * @param skuId
     * @return
     */
    BigDecimal get1010Price(Long skuId);

    List<ValueJsonTo> getBrotherSkuSaleValue (Long spuId);

}




