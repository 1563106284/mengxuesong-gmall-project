package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;

import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述：
 * 销售属性接口
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/13 21:20
 * http://192.168.200.1/admin/product/baseSaleAttrList
 */
@Api(tags = "基本销售属性接口")
@RestController
@RequestMapping("/admin/product")
public class BaseSaleStrController {
    @Autowired
    BaseSaleAttrService baseSaleAttrService;

    /**
     * 1:获取所有的基础销售属性接口
     */
    @ApiOperation(value = "获取所有的销售属性")
    @RequestMapping("/baseSaleAttrList")
    public Result getBaseSaleAttrList() {
        List<BaseSaleAttr> saleAttrList = baseSaleAttrService.list();
        return Result.ok(saleAttrList);
    }
}
