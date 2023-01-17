package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author mengxueshong
* @description 针对表【sku_attr_value(sku平台属性值关联表)】的数据库操作Mapper
* @createDate 2022-11-06 21:15:10
* @Entity com.atguigu.gmall.product.domain.SkuAttrValue
*/
@Mapper
public interface SkuAttrValueMapper extends BaseMapper<SkuAttrValue> {

}




