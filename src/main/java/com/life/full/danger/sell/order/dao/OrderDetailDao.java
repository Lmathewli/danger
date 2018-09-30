package com.life.full.danger.sell.order.dao;

import com.life.full.danger.sell.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/9/25 15:21
 * @description
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
