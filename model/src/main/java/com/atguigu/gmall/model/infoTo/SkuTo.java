package com.atguigu.gmall.model.infoTo;

import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/25 22:14
 */
@Data
public class SkuTo {
//    private CategoryView categoryView;

    private SkuInfo skuInfo;
    private List<SpuSaleAttr> spuSaleAttrList;
    private List<SkuImage> skuImages;
    private String valuesSkuJson;
}
