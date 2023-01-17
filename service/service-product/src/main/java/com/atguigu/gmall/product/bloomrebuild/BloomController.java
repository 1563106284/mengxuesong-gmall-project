package com.atguigu.gmall.product.bloomrebuild;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.bloomrebuild.service.BloomDataQueryService;
import com.atguigu.gmall.product.bloomrebuild.service.RebuildBloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2023/1/16 15:49
 */
@Controller
@RequestMapping("rebuild")
public class BloomController {
    @Autowired
    RebuildBloomFilterService bloomFilterService;
    @Autowired
    BloomDataQueryService queryService;
    @RequestMapping("/{bloomName}")
    public Result rebuildBloom(@PathVariable("bloom")String bloomName){
       Boolean result= bloomFilterService.bloomFilter(bloomName,queryService);
        return Result.ok(result);
    }
}
