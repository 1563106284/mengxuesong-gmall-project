package com.atguigu.gmall.product.service;



import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category2(二级分类表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface BaseCategory2Service extends IService<BaseCategory2> {
    /**
     *    2:查询分类数据树形结构的数据
     * @return
     */
    List<CategoryTreeTo> getCategory();
    /**
     *  根据1级分类id 查询 2级分类属性
     * @param c1Id
     * @return
     */
    List<BaseCategory2> getTwoCategory(int c1Id);


}
