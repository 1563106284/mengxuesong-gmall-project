package com.atguigu.gmall.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 商品表
 * @TableName spu_info
 */
@TableName(value ="spu_info")
@Data
public class SpuInfo implements Serializable {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 商品描述(后台简述）
     */
    private String description;

    /**
     * 三级分类id
     */
    private Long category3Id;

    /**
     * 品牌id
     */
    private Long tmId;

    @TableField(
            exist = false
    )
    private List<SpuSaleAttr> spuSaleAttrList;
    @TableField(
            exist = false
    )
    private List<SpuImage> spuImageList;


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
        SpuInfo other = (SpuInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuName() == null ? other.getSpuName() == null : this.getSpuName().equals(other.getSpuName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCategory3Id() == null ? other.getCategory3Id() == null : this.getCategory3Id().equals(other.getCategory3Id()))
            && (this.getTmId() == null ? other.getTmId() == null : this.getTmId().equals(other.getTmId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuName() == null) ? 0 : getSpuName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCategory3Id() == null) ? 0 : getCategory3Id().hashCode());
        result = prime * result + ((getTmId() == null) ? 0 : getTmId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spuName=").append(spuName);
        sb.append(", description=").append(description);
        sb.append(", category3Id=").append(category3Id);
        sb.append(", tmId=").append(tmId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}