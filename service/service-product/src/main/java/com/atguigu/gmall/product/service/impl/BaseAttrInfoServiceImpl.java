package com.atguigu.gmall.product.service.impl;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-11-04 23:26:43
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;
    /**
     *  1:根据产品分类属性 查询属于它的平台属性
     * @param c1Id  一级分类
     * @param c2Id  二级分类
     * @param c3Id  三级分类
     * @return
     */
    @Override
    public List<BaseAttrInfo> byCategorySelectBaseAttr(Long c1Id,Long c2Id,Long c3Id) {

        List<BaseAttrInfo> attrInfos=baseAttrInfoMapper.byCategorySelectBaseAttr(c1Id,c2Id,c3Id);
        return attrInfos;
    }

}




