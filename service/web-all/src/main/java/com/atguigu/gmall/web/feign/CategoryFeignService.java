package com.atguigu.gmall.web.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/21 1:02
 */
@FeignClient("service-product")
@RequestMapping("/api/inner/rpc/product")
public interface CategoryFeignService{

    /**
     *   1:gmall首页获取分类的接口
     */
    @GetMapping("/category/tree")
     Result<List<CategoryTreeTo>> getCategory();
}
