package com.life.full.danger.sell.order.dto;

import lombok.Data;

/**
 * @author LiHongHui
 * @date 2018/9/25 17:24
 * @description
 */
@Data
public class CartDTO {
    /**
     * 商品Id
     */
    private String productId;
    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
