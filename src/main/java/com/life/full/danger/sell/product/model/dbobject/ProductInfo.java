package com.life.full.danger.sell.product.model.dbobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author LiHongHui
 * @date 2018/9/14 10:06
 * @description
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    /**
     * 名字
     */
    private String productName;
    /**
     * 单价
     */
    private BigDecimal productPrice;
    /**
     * 库存
     */
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;

    /**
     * 状态：(0, 正常)；(1, 下架)
     */
    private Integer productStatus;

    /**
     * 类目编号
     */
    private Integer categoryType;
    /**
     * 小图链接
     */
    private String productIcon;
}
