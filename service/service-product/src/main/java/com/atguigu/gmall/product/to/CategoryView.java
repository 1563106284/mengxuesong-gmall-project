package com.atguigu.gmall.product.to;

import lombok.Data;

/**
 * 功能描述：
 *   封装的分类信息的数据
 * @Author: mengzhengjin
 * @Date: 2022/11/26 0:35
 */
@Data
public class CategoryView {

    private Long category1Id;
    private String category1Name;
    private Long category2Id;
    private String category2Name;
    private Long category3Id;
    private String category3Name;
}
