package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_attr_info(属性表)】的数据库操作Mapper
* @createDate 2022-11-06 21:15:10
* @Entity com.atguigu.gmall.product.domain.BaseAttrInfo
*/
@Mapper
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {


    /**
     *  1:根据分类属性 查询平台属性
     * @param c1Id
     * @param c2Id
     * @param c3Id
     * @return
     */
    List<BaseAttrInfo> byCategoryGetBaseAttrInfo(@Param("c1Id") String c1Id, @Param("c2Id") String c2Id, @Param("c3Id") String c3Id);
}




