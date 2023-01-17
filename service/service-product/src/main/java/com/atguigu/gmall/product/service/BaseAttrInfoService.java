package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {


    /**
     *  2:保存平台属性
     * @param baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     *  1：根据分类属性查询平台属性
     * @param c1Id
     * @param c2Id
     * @param c3Id
     * @return
     */
    List<BaseAttrInfo> byCategoryGetBaseAttrInfo(String c1Id, String c2Id, String c3Id);

}
