package com.atguigu.gmall.model.infoTo;

import lombok.Data;

import java.util.List;

/**
 * 功能描述：
 *   首页分类数据封装的to：
 *   ddd：思想 domain driven design
 *   面对相同的类的可以借鉴这么封装
 * @Author: mengzhengjin
 * @Date: 2022/11/20 18:45
 */
@Data
public class CategoryTo {

    private  Long categoryId;
    private  String categoryName;
    private List<CategoryTo> categoryChild;
}
