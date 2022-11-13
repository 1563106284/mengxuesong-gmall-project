package com.atguigu.gmall.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * spu销售属性
 * @TableName spu_sale_attr
 */
@TableName(value ="spu_sale_attr")
@Data
public class SpuSaleAttr implements Serializable {
    /**
     * 编号(业务中无关联)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * 销售属性id
     */
    private Long baseSaleAttrId;

    /**
     * 销售属性名称(冗余)
     */
    private String saleAttrName;

    /**
     *  销售属性值集合
     */
    @TableField(
            exist = false
    )
    private List<SpuSaleAttrValue> spuSaleAttrValueList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SpuSaleAttr other = (SpuSaleAttr) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
            && (this.getBaseSaleAttrId() == null ? other.getBaseSaleAttrId() == null : this.getBaseSaleAttrId().equals(other.getBaseSaleAttrId()))
            && (this.getSaleAttrName() == null ? other.getSaleAttrName() == null : this.getSaleAttrName().equals(other.getSaleAttrName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getBaseSaleAttrId() == null) ? 0 : getBaseSaleAttrId().hashCode());
        result = prime * result + ((getSaleAttrName() == null) ? 0 : getSaleAttrName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spuId=").append(spuId);
        sb.append(", baseSaleAttrId=").append(baseSaleAttrId);
        sb.append(", saleAttrName=").append(saleAttrName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}