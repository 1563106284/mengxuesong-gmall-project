package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseCategory3;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category3(三级分类表)】的数据库操作Service
* @createDate 2022-11-03 23:31:56
*/
public interface BaseCategory3Service extends IService<BaseCategory3> {
    /**
     *  1:根据2级属性id查询 3级商品的分类
     * @param c2Id
     * @return
     */
    List<BaseCategory3> getThreeCategory(String c2Id);
}
