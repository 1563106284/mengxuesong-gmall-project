package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author mengxueshong
* @description 针对表【spu_info(商品表)】的数据库操作Mapper
* @createDate 2022-11-06 21:15:10
* @Entity com.atguigu.gmall.product.domain.SpuInfo
*/
@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {

}




