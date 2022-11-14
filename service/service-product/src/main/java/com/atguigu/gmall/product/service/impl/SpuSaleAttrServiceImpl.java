package com.atguigu.gmall.product.service.impl;


import com.atguigu.gmall.product.domain.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author mengxueshong
 * @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service实现
 * @createDate 2022-11-06 21:15:10
 */
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr>
        implements SpuSaleAttrService {

    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;

    /**
     * 1:根据spuId 查询出销售属性的名 和值
     *
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> spuSaleAttrList(Long spuId) {
        List<SpuSaleAttr> list = spuSaleAttrMapper.spuSaleAttrList(spuId);
        return list;
    }


}



