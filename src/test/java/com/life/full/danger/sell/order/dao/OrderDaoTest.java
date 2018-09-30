package com.life.full.danger.sell.order.dao;

import com.life.full.danger.sell.order.model.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author LiHongHui
 * @date 2018/9/25 15:28
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {
    @Autowired
    private OrderDao orderDao;

    private final String OPENID = "110110";


    @Test
    public void saveTest() {
        OrderMaster order = new OrderMaster();
        order.setOrderId("1234567");
        order.setBuyerName("mathew.li");
        order.setBuyerPhone("13112345678");
        order.setBuyerAddress("慕课网");
        order.setBuyerOpenid(OPENID);
        order.setOrderAmount(new BigDecimal(2.3));
        OrderMaster result = orderDao.save(order);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(1, 3);
        Page<OrderMaster> result = orderDao.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}