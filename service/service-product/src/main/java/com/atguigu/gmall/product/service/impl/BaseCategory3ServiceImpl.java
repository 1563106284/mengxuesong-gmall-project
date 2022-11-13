package com.atguigu.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.domain.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author mengxueshong
* @description 针对表【base_category3(三级分类表)】的数据库操作Service实现
* @createDate 2022-11-06 21:15:10
*/
@Service
public class BaseCategory3ServiceImpl extends ServiceImpl<BaseCategory3Mapper, BaseCategory3>
    implements BaseCategory3Service{
    @Autowired
    BaseCategory3Mapper baseCategory3Mapper;
    /**
     *  根据2级分类查询3级分类
     * @param c2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getThreeCategory(String c2Id) {
        QueryWrapper queryWrapper= new QueryWrapper<BaseCategory3>();
        queryWrapper.eq("category2_id",c2Id);
        List list = baseCategory3Mapper.selectList(queryWrapper);
        return list;
    }
}




