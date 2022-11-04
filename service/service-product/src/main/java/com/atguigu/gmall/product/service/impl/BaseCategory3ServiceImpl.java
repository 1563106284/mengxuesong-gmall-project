package com.atguigu.gmall.product.service.impl;


import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category3(三级分类表)】的数据库操作Service实现
* @createDate 2022-11-03 23:31:56
*/
@Service
public class BaseCategory3ServiceImpl extends ServiceImpl<BaseCategory3Mapper, BaseCategory3>
    implements BaseCategory3Service{

    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;
    /**
     *  1:根据2级属性id查询 3级商品的分类
     * @param c2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getThreeCategory(String c2Id) {
        QueryWrapper<BaseCategory3> queryWrapper=new QueryWrapper<>();
        queryWrapper.equals(c2Id);
        List<BaseCategory3> category3List = baseCategory3Mapper.selectList(queryWrapper);
        return category3List;
    }
}




