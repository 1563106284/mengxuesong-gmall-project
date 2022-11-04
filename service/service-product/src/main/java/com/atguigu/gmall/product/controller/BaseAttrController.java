package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.service.BaseAttrInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  与分类属性关联的平台属性名和值的接口
 */
@RestController
@RequestMapping("/admin/product")
public class BaseAttrController {


    @Autowired
    BaseAttrInfoService baseAttrInfoService;

    /**
     * 1： 根据产品分类的属性 查询出对应的平台属性
     * /admin/product/attrInfoList/6/6/0
     */
    @GetMapping("/attrInfoList/{c1Id}/{c2Id}/{c3Id}")
    public Result getBaseAttr(@PathVariable("c1Id")Long c1Id,
                              @PathVariable("c2Id")Long c2Id,
                            @PathVariable("c3Id")Long c3Id){

       List<BaseAttrInfo> attrInfos=baseAttrInfoService.byCategorySelectBaseAttr(c1Id,c2Id,c3Id);
        return Result.ok(attrInfos);
    }
}
