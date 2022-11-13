package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.domain.BaseCategory2;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category2(二级分类表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface BaseCategory2Service extends IService<BaseCategory2> {
    /**
     *  根据1级分类id 查询 2级分类属性
     * @param c1Id
     * @return
     */
    List<BaseCategory2> getTwoCategory(int c1Id);
}
