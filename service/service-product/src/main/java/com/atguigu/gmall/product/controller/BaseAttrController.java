package com.atguigu.gmall.product.controller;



import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.domain.BaseAttrInfo;
import com.atguigu.gmall.product.domain.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *  与分类属性关联的平台属性名和值的接口
 */
@RestController
@RequestMapping("/admin/product")
public class BaseAttrController {
    @Autowired
    BaseAttrInfoService baseAttrInfoService;
    @Autowired
    BaseAttrValueService baseAttrValueService;

    /**
     *   3: 修改前的数据回显
     *   /admin/product/getAttrValueList/1
     */
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId")String attrId){
        List<BaseAttrValue> attrValueList = baseAttrValueService.getAttrValueList(attrId);
        return Result.ok(attrValueList);
    }

    /**
     *  2: 保存添加的平台属性
     *  saveAttrInfo
     *  //192.168.200.1/admin/product/saveAttrInfo
     */
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        baseAttrInfoService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     *   1: 根据分类属性 查询平台的 属性
     *  /admin/product/attrInfoList/5/30/0
     */
    @GetMapping("/attrInfoList/{c1Id}/{c2Id}/{c3Id}")
    public Result byCategoryGetBaseAttrInfo(@PathVariable("c1Id")String c1Id,
                                            @PathVariable("c2Id")String c2Id,
                                            @PathVariable("c3Id")String c3Id){

        List<BaseAttrInfo> list=baseAttrInfoService.byCategoryGetBaseAttrInfo(c1Id,c2Id,c3Id);
        return Result.ok(list);
    }
}
