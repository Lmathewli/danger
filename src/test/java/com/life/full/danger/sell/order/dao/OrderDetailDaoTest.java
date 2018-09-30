package com.life.full.danger.sell.order.dao;

import com.life.full.danger.sell.order.model.OrderDetail;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author LiHongHui
 * @date 2018/9/25 16:11
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest() {
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("12345678910");
        detail.setOrderId("1111111");
        detail.setProductIcon("http://xxx.jpg");
        detail.setProductId("111");
        detail.setProductName("皮蛋粥");
        detail.setProductPrice(new BigDecimal(1.2));
        detail.setProductQuantity(2);
        OrderDetail result = orderDetailDao.save(detail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("1111111");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}