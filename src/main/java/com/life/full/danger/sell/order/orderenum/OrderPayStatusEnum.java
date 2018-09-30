package com.life.full.danger.sell.order.orderenum;

import lombok.Getter;

/**
 * @author LiHongHui
 * @date 2018/9/25 15:03
 * @description
 */
@Getter
public enum OrderPayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");
    private Integer code;
    private String message;

    OrderPayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}