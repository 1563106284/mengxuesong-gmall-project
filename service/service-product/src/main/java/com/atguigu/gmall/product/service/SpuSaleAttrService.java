package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface SpuSaleAttrService extends IService<SpuSaleAttr> {
    /**
     *  2:获取spu信息
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuDate(Long skuId, Long spuId);
    /**
     *  1:根据spuId 查询出销售属性名 +值
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> spuSaleAttrList(Long spuId);

}
