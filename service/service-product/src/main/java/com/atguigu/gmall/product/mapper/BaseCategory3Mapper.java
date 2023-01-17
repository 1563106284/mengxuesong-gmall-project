package com.atguigu.gmall.product.mapper;


import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.product.to.CategoryView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author mengxueshong
* @description 针对表【base_category3(三级分类表)】的数据库操作Mapper
* @createDate 2022-11-06 21:15:10
* @Entity com.atguigu.gmall.product.domain.BaseCategory3
*/
@Mapper
public interface BaseCategory3Mapper extends BaseMapper<BaseCategory3> {
    /**
     *  1:根基3级分类id获取对应的信息
     * @param category3Id
     * @return
     */
    CategoryView getCategoryInfo(Long category3Id);
}




