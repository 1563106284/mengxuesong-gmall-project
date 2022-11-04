package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2022-11-04 23:26:43
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {
    /**
     *  1:根据产产品分类属性 查询属于它的平台属性
     * @param c1Id
     * @param c2Id
     * @param c3Id
     * @return
     */
    List<BaseAttrInfo> byCategorySelectBaseAttr(Long c1Id, Long c2Id,Long c3Id);

}
