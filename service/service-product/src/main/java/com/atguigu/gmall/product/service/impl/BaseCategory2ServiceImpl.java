package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategory2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.mapper.BaseCategory2Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category2(二级分类表)】的数据库操作Service实现
* @createDate 2022-11-03 23:31:56
*/
@Service
public class BaseCategory2ServiceImpl extends ServiceImpl<BaseCategory2Mapper, BaseCategory2>
    implements BaseCategory2Service{

    @Autowired
    BaseCategory2Mapper baseCategory2Mapper;
    /**
     * 1：根据1级分类商品id获取2级分类商品属性
     * @param c1Id
     * @return
     */
    @Override
    public List<BaseCategory2> getTwoCategory(int c1Id) {

        QueryWrapper<BaseCategory2> queryWrapper=new QueryWrapper<>();
        queryWrapper.equals(c1Id);
        List<BaseCategory2> category2s = baseCategory2Mapper.selectList(queryWrapper);

        return category2s;
    }
}




