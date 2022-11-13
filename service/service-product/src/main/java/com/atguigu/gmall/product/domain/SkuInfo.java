package com.atguigu.gmall.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 库存单元表
 * @TableName sku_info
 */
@TableName(value ="sku_info")
@Data
public class SkuInfo implements Serializable {
    /**
     * 库存id(itemID)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * 价格
     */
    private Integer price;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 商品规格描述
     */
    private String skuDesc;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 品牌(冗余)
     */
    private Long tmId;

    /**
     * 三级分类id（冗余)
     */
    private Long category3Id;

    /**
     * 默认显示图片(冗余)
     */
    private String skuDefaultImg;

    /**
     * 是否销售（1：是 0：否）
     */
    private Integer isSale;

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
        SkuInfo other = (SkuInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getSkuName() == null ? other.getSkuName() == null : this.getSkuName().equals(other.getSkuName()))
            && (this.getSkuDesc() == null ? other.getSkuDesc() == null : this.getSkuDesc().equals(other.getSkuDesc()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getTmId() == null ? other.getTmId() == null : this.getTmId().equals(other.getTmId()))
            && (this.getCategory3Id() == null ? other.getCategory3Id() == null : this.getCategory3Id().equals(other.getCategory3Id()))
            && (this.getSkuDefaultImg() == null ? other.getSkuDefaultImg() == null : this.getSkuDefaultImg().equals(other.getSkuDefaultImg()))
            && (this.getIsSale() == null ? other.getIsSale() == null : this.getIsSale().equals(other.getIsSale()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getSkuName() == null) ? 0 : getSkuName().hashCode());
        result = prime * result + ((getSkuDesc() == null) ? 0 : getSkuDesc().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getTmId() == null) ? 0 : getTmId().hashCode());
        result = prime * result + ((getCategory3Id() == null) ? 0 : getCategory3Id().hashCode());
        result = prime * result + ((getSkuDefaultImg() == null) ? 0 : getSkuDefaultImg().hashCode());
        result = prime * result + ((getIsSale() == null) ? 0 : getIsSale().hashCode());
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
        sb.append(", price=").append(price);
        sb.append(", skuName=").append(skuName);
        sb.append(", skuDesc=").append(skuDesc);
        sb.append(", weight=").append(weight);
        sb.append(", tmId=").append(tmId);
        sb.append(", category3Id=").append(category3Id);
        sb.append(", skuDefaultImg=").append(skuDefaultImg);
        sb.append(", isSale=").append(isSale);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}