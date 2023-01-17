package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;

import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：
 *  spu 一个类型商品的属性
 *  sku 一个具体商品的属性
 * @Author: mengzhengjin
 * @Date: 2022/11/13 19:50
 *
 */
@Api(tags = "spu属性接口")
@RestController
@RequestMapping("/admin/product")
public class SpuController {
    @Autowired
    SpuInfoService spuInfoService;

    /**
     *  2:
     *  保存spu 大json 数据：
     *  http://192.168.200.1/admin/product/saveSpuInfo
     */
    @ApiOperation(value = "保存spu属性数据")
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        System.out.println(spuInfo);
        spuInfoService.saveSpuInfo(spuInfo);
        return Result.ok();
    }
    /**
     *   http://192.168.200.1/admin/product/1/10?category3Id=14
     * @requestparam: ?category3Id=14 接的是问号后面参数 请求参数
     * @reuqebody 请求参数+请求体的参数有
     * @pathvariable:接路径参数 问号前面
     * @requeshead：接请求头
     * get ：有请求头 content-type：就是请求体
     * post ：有请求体 任意参数
     */
    @ApiOperation(value = "spu分页信息")
    @GetMapping("/{pageNum}/{pageSize}")
    public Result spuPage(@PathVariable("pageNum")Long pageNum,
                          @PathVariable("pageSize")Long pageSize,
                          @RequestParam("category3Id")Long category3Id){
        Page<SpuInfo> page = new Page(pageNum, pageSize);
        QueryWrapper<SpuInfo> queryWrapper=new QueryWrapper();
        queryWrapper.eq("category3_id",category3Id);
        Page<SpuInfo> infoPage = spuInfoService.page(page, queryWrapper);
        return Result.ok(infoPage);
    }
}
