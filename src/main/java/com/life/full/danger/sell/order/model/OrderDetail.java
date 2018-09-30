package com.life.full.danger.sell.order.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author LiHongHui
 * @date 2018/9/25 15:18
 * @description
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private String detailId;
    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 商品Id
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 商品小图
     */
    private String productIcon;
}
