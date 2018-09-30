package com.life.full.danger.sell.order.service.impl;

import com.life.full.danger.sell.order.dto.OrderDTO;
import com.life.full.danger.sell.order.model.OrderDetail;
import com.life.full.danger.sell.order.model.OrderMaster;
import com.life.full.danger.sell.order.orderenum.OrderPayStatusEnum;
import com.life.full.danger.sell.order.orderenum.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author LiHongHui
 * @date 2018/9/25 17:35
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    private final String buyerOpenid = "110110";
    private final String orderId = "1537930250590361911";
    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("mathew.li");
        orderDTO.setBuyerAddress("sh");
        orderDTO.setBuyerPhone("13112341234");
        orderDTO.setBuyerOpenid(buyerOpenid);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(10);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result = {}", result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        log.info("【创建订单】 result = {}", orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0, 1);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, pageRequest);
        log.info("【创建订单】 result = {}", orderDTOPage);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.cancel.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(OrderPayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }
}