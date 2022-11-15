package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.domain.SkuAttrValue;
import com.atguigu.gmall.product.domain.SkuImage;
import com.atguigu.gmall.product.domain.SkuSaleAttrValue;
import com.atguigu.gmall.product.service.SkuAttrValueService;
import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.service.SkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.domain.SkuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.List;

/**
 * @author mengxueshong
 * @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
 * @createDate 2022-11-06 21:15:10
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
        implements SkuInfoService {
    @Autowired
    SkuInfoService skuInfoService;
    @Autowired
    SkuImageService skuImageService;
    @Autowired
    SkuAttrValueService skuAttrValueService;
    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 1:sku数据的大保存
     *
     * @param skuInfo
     * @return
     */
    @Override
    public List<SkuInfo> saveSkuInfo(SkuInfo skuInfo) {
        // 1：保存sku info信息
        save(skuInfo);
        Long id = skuInfo.getId();
        //2: 保存sku的照片到库中
        for (SkuImage skuImage : skuInfo.getSkuImageList()) {
            //3：遍历添加数据
            skuImage.setSkuId(id);
            skuImageService.saveBatch(skuInfo.getSkuImageList());
        }
        // 3:保存sku的平台属性 和赋值关系保存： sku——attr-value
        List<SkuAttrValue> skuAttrValues = skuInfo.getSkuAttrValues();
        for (SkuAttrValue skuAttrValue : skuAttrValues) {
            skuAttrValue.setSkuId(skuInfo.getId());
        }
        skuAttrValueService.saveBatch(skuAttrValues);

        //sku 的销售属性名 和值： sku——sale——attr——value
        List<SkuSaleAttrValue> skuSaleAttrValues = skuInfo.getSkuSaleAttrValues();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValues) {
            skuSaleAttrValue.setSkuId(skuInfo.getId());
//            skuSaleAttrValue.setSpuId(skuInfo.getSpuId());

        }

        return null;
    }
}




