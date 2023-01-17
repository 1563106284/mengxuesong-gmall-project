package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeTo;

import com.atguigu.gmall.web.feign.CategoryFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

/**
 * 功能描述：
 * 首页：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/19 7:39
 */

@Controller
public class IndexController {
    @Autowired
    CategoryFeignService categoryFeignService;

    @GetMapping({"/", "index"})
    public String indexPage(Model model) {
        Result<List<CategoryTreeTo>> category = categoryFeignService.getCategory();
        if (category.isOk()) {
            List<CategoryTreeTo> data = category.getData();
            model.addAttribute("list", data);
        }

        return "index/index";// 逻辑视图名



    }
}
