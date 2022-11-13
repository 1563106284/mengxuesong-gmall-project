package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.domain.BaseAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_attr_value(属性值表)】的数据库操作Service
* @createDate 2022-11-06 21:15:10
*/
public interface BaseAttrValueService extends IService<BaseAttrValue> {
    /**
     *  1:查询修改平台属性功能前的数据回显
     * @param attrId
     */
    List<BaseAttrValue> getAttrValueList(String attrId);
}
