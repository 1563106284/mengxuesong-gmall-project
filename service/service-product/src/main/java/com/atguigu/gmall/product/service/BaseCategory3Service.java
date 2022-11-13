package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.domain.BaseCategory3;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category3(三级分类表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface BaseCategory3Service extends IService<BaseCategory3> {
    /**
     *  根据2级分类查询3级分类
     * @param c2Id
     * @return
     */
    List<BaseCategory3> getThreeCategory(String c2Id);
}
