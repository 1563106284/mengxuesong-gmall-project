package com.atguigu.gmall.product.controller.api;

import com.atguigu.gmall.common.result.Result;

import com.atguigu.gmall.model.to.CategoryTreeTo;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述：
 *  内部调用的接口：格式 /api/inner/rpg/+名字
 * @Author: mengzhengjin
 * @Date: 2022/11/20 18:26
 */
@RestController
@RequestMapping("/api/inner/rpc/product")
@Api(tags = "首页内部调用接口")
public class CategoryInnerApiController {
    @Autowired
    BaseCategory2Service baseCategory2Service;
    /**
     *   1:gmall首页获取分类的接口
     */
    @ApiOperation(value = "首页分类数据")
    @GetMapping("/category/tree")
    public Result getCategory(){
       List<CategoryTreeTo> categoryTree =baseCategory2Service.getCategory();

        return Result.ok(categoryTree);
    }
}
