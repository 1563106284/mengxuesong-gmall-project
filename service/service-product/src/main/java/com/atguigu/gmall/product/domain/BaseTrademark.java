package com.atguigu.gmall.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 品牌表
 * @TableName base_trademark
 */
@TableName(value ="base_trademark")
@Data
public class BaseTrademark implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 属性值
     */
    private String tmName;

    /**
     * 品牌logo的图片路径
     */
    private String logoUrl;

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
        BaseTrademark other = (BaseTrademark) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTmName() == null ? other.getTmName() == null : this.getTmName().equals(other.getTmName()))
            && (this.getLogoUrl() == null ? other.getLogoUrl() == null : this.getLogoUrl().equals(other.getLogoUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTmName() == null) ? 0 : getTmName().hashCode());
        result = prime * result + ((getLogoUrl() == null) ? 0 : getLogoUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tmName=").append(tmName);
        sb.append(", logoUrl=").append(logoUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}