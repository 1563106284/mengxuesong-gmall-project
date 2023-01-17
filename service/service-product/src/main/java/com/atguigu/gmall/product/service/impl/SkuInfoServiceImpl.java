package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.constant.SystemRedisConstant;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;

import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.service.SkuAttrValueService;
import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.service.SkuSaleAttrValueService;

import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;

import com.atguigu.gmall.product.to.CategoryView;
import com.atguigu.gmall.product.to.SkuDetailInfoTo;
import com.atguigu.gmall.product.to.ValueJsonTo;
import com.atguigu.gmall.product.util.JsonUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;
    @Autowired
    SkuImageMapper skuImageMapper;
    @Autowired
    SkuInfoMapper skuInfoMapper;
    @Autowired
    SpuSaleAttrMapper saleAttrMapper;

    /**
     *  4:查询到所有的sku id
     * @return
     */
    @Override
    public List<Long> saveSkuId() {
        List<Long> skuIds=skuInfoMapper.selectSkuId();
        return skuIds;
    }

    @Override
    public String getBrotherSkuSaleValue(Long spuId) {

        List<ValueJsonTo> saleValue = skuInfoMapper.getBrotherSkuSaleValue(spuId);
        // 6.1:格式转化：将值为key id为value：
        Map<String, String> valueJson = new HashMap<>();
        saleValue.forEach(c -> {
            valueJson.put(c.getValueJson(), c.getSkuId());
        });
        String brotherInfo = JsonUtil.toStr(valueJson);
        return brotherInfo;
    }

    @Override
    public Result<SkuInfo> getSkuInfo(Long skuId) {
        SkuInfo info = skuInfoService.getById(skuId);
        return Result.ok(info);
    }

    /**
     * 3:查询sku的实时价格
     */
    @Override
    public BigDecimal getPrice(Long skuId) {

        BigDecimal price = skuInfoMapper.get1010Price(skuId);
        return price;
    }

    /**
     * 2:sku的首页的详情数据
     * @deprecate：弃用
     * @param skuId
     * @return
     */
    @Deprecated
    @Override
    public SkuDetailInfoTo getSkuDetail(Long skuId) {
        SkuDetailInfoTo skuDetailInfoTo = new SkuDetailInfoTo();
        // 1：获取3级分类的id：
        SkuInfo info = skuInfoService.getById(skuId);
        skuDetailInfoTo.setSkuInfo(info);
        // 2:根据3级分类的id获取sku info信息
        CategoryView categoryView = baseCategory3Mapper.getCategoryInfo(info.getCategory3Id());
        skuDetailInfoTo.setCategoryView(categoryView);

        // 3:获取图片：
        QueryWrapper<SkuImage> queryWapper = new QueryWrapper<>();
        queryWapper.eq("sku_id", skuId);
        List<SkuImage> imageList = skuImageMapper.selectList(queryWapper);
        skuDetailInfoTo.setSkuImages(imageList);

        // 4:获取实时的价格：
        skuDetailInfoTo.setPrice(this.getPrice(skuId));

        // 5:获取sku定义的spu 销售属性的名和值，会高亮的展示

        skuDetailInfoTo.setSpuSaleAttrList(saleAttrMapper.getSpuDate(info.getSpuId(), skuId));

        // 6:获取其他兄弟产品的属性：valueJson：
        Long spuId = info.getSpuId();
        List<ValueJsonTo> saleValue = skuInfoMapper.getBrotherSkuSaleValue(spuId);
        // 6.1:格式转化：将值为key id为value：
        Map<String, String> valueJson = new HashMap<>();
        saleValue.forEach(c -> {
            valueJson.put(c.getValueJson(), c.getSkuId());
        });
//        for (ValueJsonTo valueJsonTo : saleValue) {
//            valueJson.put(valueJsonTo.getValue_json(),valueJsonTo.getSku_id());
//        }
        skuDetailInfoTo.setValuesSkuJson(JsonUtil.toStr(valueJson));
        return skuDetailInfoTo;
    }

    /**
     * 1:sku数据的大保存
     *
     * @param skuInfo
     * @return
     */
    @Autowired
    RedissonClient redissonClient;
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //  1:SKU基本的保存：
        save(skuInfo);
        Long id = skuInfo.getId();
        // 2:sku的图片保存到sku image 里面：
        for (SkuImage skuImage : skuInfo.getSkuImageList()) {
            skuImage.setSkuId(id);
        }
        // 批量多的比如list 可以使用bath：保存
        skuImageService.saveBatch(skuInfo.getSkuImageList());

        // 3:保存sku平台属性的名： 以及值：
        for (SkuAttrValue skuAttrValue : skuInfo.getSkuAttrValueList()) {
            skuAttrValue.setSkuId(id);
        }
        skuAttrValueService.saveBatch(skuInfo.getSkuAttrValueList());

        // 4:保存sku的销售属性和值

        for (SkuSaleAttrValue skuSaleAttrValue : skuInfo.getSkuSaleAttrValueList()) {
            skuSaleAttrValue.setSkuId(id);
        }
        skuSaleAttrValueService.saveBatch(skuInfo.getSkuSaleAttrValueList());

        // 5:使用@postconstruct:布隆初始化加载了所有id+ 新增商品的时候添加skuId=所有布隆sku id数据
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(SystemRedisConstant.BLOOM_SKUID);
        bloomFilter.add(skuInfo.getId());
    }
}




