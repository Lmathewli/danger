package com.life.full.danger.sell.order.service;

import com.life.full.danger.sell.order.dto.OrderDTO;

/**
 * 买家
 * @author LiHongHui
 * @date 2018/9/28 11:03
 * @description
 */
public interface BuyerService {

    /**
     * 查询一个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     *     取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
