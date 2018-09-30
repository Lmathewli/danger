package com.life.full.danger.sell.order.service.impl;

import com.life.full.danger.sell.order.dto.OrderDTO;
import com.life.full.danger.sell.order.exception.SellException;
import com.life.full.danger.sell.order.orderenum.ResultEnum;
import com.life.full.danger.sell.order.service.BuyerService;
import com.life.full.danger.sell.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author LiHongHui
 * @date 2018/9/28 11:05
 * @description
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    /**
     * 查询一个订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            log.error("【查询订单】订单不存在，orderId = {}", orderId);
            throw new SellException(ResultEnum.order_not_exist);
        }
        if (!Objects.equals(orderDTO.getBuyerOpenid(), openid)) {
            log.error("【查询订单】 订单的openid不一致。openid = {}，orderDTO = {}", openid, orderDTO);
            throw new SellException(ResultEnum.order_owner_error);
        }
        return orderDTO;
    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = findOrderOne(openid, orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        return result;
    }
}
