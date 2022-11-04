package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_attr_info(属性表)】的数据库操作Mapper
* @createDate 2022-11-04 23:26:43
* @Entity com.atguigu.gmall.product.domain.BaseAttrInfo
*/
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {
    /**
     *  1:根据产品分类属性 查询属于它的平台属性
     * @param c1Id  一级分类
     * @param c2Id  二级分类
     * @param c3Id  三级分类
     * @return
     */
    List<BaseAttrInfo> byCategorySelectBaseAttr(@Param("c1Id") Long c1Id, @Param("c2Id") Long c2Id, @Param("c3Id") Long c3Id);
}




