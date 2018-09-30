package com.life.full.danger.sell.order.orderenum;

import lombok.Getter;

/**
 * @author LiHongHui
 * @date 2018/9/25 17:02
 * @description
 */
@Getter
public enum ResultEnum {
    param_error(1, "参数不正确"),
    product_not_exist(10, "商品不存在"),
    product_stock_error(11, "商品库存不正确"),
    order_not_exist(12, "订单不存在"),
    order_detail_not_exist(13, "订单详情不存在"),
    order_status_error(14, "订单状态不符合"),
    order_update_error(15, "订单更新失败"),
    order_pay_status_error(16, "订单支付状态不正确"),
    cart_empty(17, "购物车不能为空"), order_owner_error(18, "该订单不属于当前用户"), wx_mp_error(20, "微信公众号错误");

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

}
