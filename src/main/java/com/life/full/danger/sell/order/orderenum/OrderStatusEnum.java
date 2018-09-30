package com.life.full.danger.sell.order.orderenum;

import lombok.Getter;

/**
 * @author LiHongHui
 * @date 2018/9/25 15:03
 * @description
 */
@Getter
public enum OrderStatusEnum {
    NEW_ORDER(0, "新订单"),
    FINISHED(1, "完结"),
    cancel(2, "已取消");
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}