package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.domain.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.checkerframework.checker.units.qual.min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 品牌分页：展示
 * http://192.168.200.1/admin/product/baseTrademark/1/10
 */
@RestController
@RequestMapping("/admin/product")
public class baseTrademarkController {
    @Autowired
    BaseTrademarkService baseTrademarkService;




    /**
     *  6:图片上传
     *  http://192.168.200.1/admin/product/fileUpload
     */
    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestPart("file") MultipartFile file) throws Exception {

        System.out.println(file);
         baseTrademarkService.uploadFile(file);

        return Result.ok();
    }

    /**
     * 5:新增 保存
     * http://192.168.200.1/admin/product/baseTrademark/save
     */
    @PostMapping("/baseTrademark/save")
    public Result save(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    /**
     * 4:修改
     * http://192.168.200.1/admin/product/baseTrademark/update
     */
    @PutMapping("/baseTrademark/update")
    public Result updateTrademark(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    /**
     * 3:删除
     * http://192.168.200.1/admin/product/baseTrademark/remove/2
     */
    @DeleteMapping("/baseTrademark/remove/{trademarkId}")
    public Result removeTrademark(@PathVariable("trademarkId") Long id) {
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    /**
     * 2:http://192.168.200.1/admin/product/baseTrademark/get/2
     * 根据id查询品牌信息
     */
    @GetMapping("/baseTrademark/get/{trademarkId}")
    public Result<BaseTrademark> getTrademarkId(@PathVariable("trademarkId") Long id) {
        BaseTrademark trademark = baseTrademarkService.getById(id);
        return Result.ok(trademark);
    }

    /**
     * 1: 使用的分页插件：
     * 定义page 用的爆米豆：注意看下
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/baseTrademark/{page}/{limit}")
    public Result baseTrademark(@PathVariable("page") Long page,
                                @PathVariable("limit") Long limit) {

        Page pageNum = new Page(page, limit);

        Page trademarkPage = baseTrademarkService.page(pageNum);
        return Result.ok(trademarkPage);
    }
}
