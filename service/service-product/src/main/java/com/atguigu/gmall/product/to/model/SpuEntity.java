package com.atguigu.gmall.product.to.model;

import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：
 *
 * @Author: mengzhengjin
 * @Date: 2022/11/28 0:39
 */
@Data
public class SpuEntity {
    @ApiModelProperty("商品id")
    @TableField("spu_id")
    private Long spuId;
    @ApiModelProperty("销售属性id")
    @TableField("base_sale_attr_id")
    private Long baseSaleAttrId;
    @ApiModelProperty("销售属性名称(冗余)")
    @TableField("sale_attr_name")
    private String saleAttrName;
    @TableField(
            exist = false
    )
    List<SpuSaleAttrValue> spuSaleAttrValueList;
    // 是否选中
    @TableField("is_check")
    private Integer isCheck;

}
