package com.atguigu.gmall.product.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  my batisPlus:分页插件
 */
@Configuration
public class MybatisPlusPageConfig {
    @Bean
    public MybatisPlusInterceptor pageInterceptor(){
        // 1：插件主体：
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 2:小插件：
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        innerInterceptor.setOverflow(true);// 开启页面溢出后，会自动显示最后一页

        // 3:把小插件添加到主插件中
        mybatisPlusInterceptor.addInnerInterceptor(innerInterceptor);

        return mybatisPlusInterceptor;
    }
}
