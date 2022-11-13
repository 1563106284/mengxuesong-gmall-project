package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.domain.SpuImage;
import com.atguigu.gmall.product.domain.SpuSaleAttr;
import com.atguigu.gmall.product.domain.SpuSaleAttrValue;
import com.atguigu.gmall.product.mapper.SpuImageMapper;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.domain.SpuInfo;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mengxueshong
 * @description 针对表【spu_info(商品表)】的数据库操作Service实现
 * @createDate 2022-11-06 21:15:10
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
        implements SpuInfoService {

    @Autowired
    SpuInfoMapper spuInfoMapper;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;
    @Autowired
    SpuSaleAttrValueService spuSaleAttrValueService;

    /**
     * 1:保存spu json大数据
     *
     * @param spuInfo 大量的修改数据需要开启事务： 2个注解
     * @transactional
     * @enableTransactionManagement
     */
    @Transactional
    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        // 1:保存 spu info信息数据
        spuInfoMapper.insert(spuInfo);
        // 2:保存spu 的图片信息
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        Long spuId = spuInfo.getId();
        for (SpuImage spuImage : spuImageList) {
            // 2.1; 回填spu 的id 保证有数据
            spuImage.setSpuId(spuId);
        }
        // 2.2:保存 spu图片信息
        spuImageService.saveBatch(spuImageList);

        // 3:处理销售属性名 +值
        // 3.1:回填id
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            spuSaleAttr.setSpuId(spuId);
            // 3.2:回填spu ：销售属性的值 id+名字
            List<SpuSaleAttrValue> saleAttrValues = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue saleAttrValue : saleAttrValues) {
                saleAttrValue.setSpuId(spuId);
                saleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
            }
            spuSaleAttrValueService.saveBatch(saleAttrValues);
        }
        //  3.3:保存：销售属性名 +销售属性值：
        spuSaleAttrService.saveBatch(spuSaleAttrList);

    }
}




