package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseCategory2;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category2(二级分类表)】的数据库操作Service
* @createDate 2022-11-03 23:31:56
*/
public interface BaseCategory2Service extends IService<BaseCategory2> {
    /**
     * 1：根据1级分类商品id获取2级分类商品属性
     * @param c1Id
     * @return
     */
    List<BaseCategory2> getTwoCategory(int c1Id);
}
