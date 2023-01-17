package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.to.model.SpuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mengxueshong
 * @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Mapper
 * @createDate 2022-11-06 21:15:10
 * @Entity com.atguigu.gmall.product.domain.SpuSaleAttr
 */
@Mapper
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {
    /**
     * 2: 获取sku定义的销售属性的名 和值
     *
     * @param spuId
     * @param skuId
     * @return
     */
    List<SpuSaleAttr> getSpuDate (@Param("spuId") Long spuId, @Param("skuId") Long skuId);

    /**
     * 1:获取销售属性的名和值
     *
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> spuSaleAttrList(Long spuId);

}




