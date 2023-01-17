package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SkuImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author mengxueshong
* @description 针对表【sku_image(库存单元图片表)】的数据库操作Mapper
* @createDate 2022-11-06 21:15:10
* @Entity com.atguigu.gmall.product.domain.SkuImage
*/
@Mapper
public interface SkuImageMapper extends BaseMapper<SkuImage> {

}




