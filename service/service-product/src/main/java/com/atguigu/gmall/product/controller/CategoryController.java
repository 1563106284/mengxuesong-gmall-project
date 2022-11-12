package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.domain.BaseCategory1;
import com.atguigu.gmall.product.domain.BaseCategory2;
import com.atguigu.gmall.product.domain.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 1: 分类商品属性控制层
 * 2：@Controller
 *
 * @ResponseBody =restconroller
 * 路径： admin/product/getCategory1
 */
@RestController
@RequestMapping("/admin/product")
public class CategoryController {
    @Autowired
    BaseCategory1Service baseCategory1Service;
    @Autowired
    BaseCategory2Service baseCategory2Service;
    @Autowired
    BaseCategory3Service baseCategory3Service;

    /**
     * 3:查询三级级分类商品属性的接口
     * /admin/product/getCategory3/6
     */
    @GetMapping("/getCategory3/{c2Id}")
    public Result getThreeCategory(@PathVariable("c2Id") String c2Id) {
        List<BaseCategory3> list = baseCategory3Service.getThreeCategory(c2Id);
        return Result.ok(list);
    }

    /**
     * 2:查询二级分类商品属性的接口
     * /admin/product/getCategory2/6
     */
    @GetMapping("/getCategory2/{c1Id}")
    public Result getTwoCategory(@PathVariable("c1Id") int c1Id) {
        List<BaseCategory2> list = baseCategory2Service.getTwoCategory(c1Id);
        return Result.ok(list);
    }

    /**
     * 1:查询一级分类商品属性的接口
     *
     * @return
     */
    @GetMapping("/getCategory1")
    public Result getOneCategory() {
        List<BaseCategory1> list = baseCategory1Service.list();
        return Result.ok(list);
    }
}
