package com.life.full.danger.sell.product.enums;

import lombok.Getter;

/**
 * 商品状态
 * @author LiHongHui
 * @date 2018/9/14 10:46
 * @description
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在使用"),
    DOWN(1, "下架");
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
