package com.atguigu.gmall.product.to;

import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/27 16:03
 */
@Data
public class SkuDetailInfoTo {
    private CategoryView categoryView;
    private SkuInfo skuInfo;
    private List<SpuSaleAttr> spuSaleAttrList;
    private List<SkuImage> skuImages;
    private String valuesSkuJson;
    private BigDecimal price;
}
