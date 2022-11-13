package com.atguigu.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.domain.BaseCategory2;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.mapper.BaseCategory2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category2(二级分类表)】的数据库操作Service实现
* @createDate 2022-11-06 21:15:10
*/
@Service
public class BaseCategory2ServiceImpl extends ServiceImpl<BaseCategory2Mapper, BaseCategory2>
    implements BaseCategory2Service{
    @Autowired
    BaseCategory2Mapper baseCategory2Mapper;
    @Override
    public List<BaseCategory2> getTwoCategory(int c1Id) {
        QueryWrapper queryWrapper= new QueryWrapper<BaseCategory2>();
        queryWrapper.eq("category1_id",c1Id);
        List list = baseCategory2Mapper.selectList(queryWrapper);
        return list;
    }
}




