package com.life.full.danger.sell.order.dao;

import com.life.full.danger.sell.order.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LiHongHui
 * @date 2018/9/25 15:21
 * @description
 */
public interface OrderDao extends JpaRepository<OrderMaster, String> {
    /**
     * 根据买家的openId来查询，并且分页
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
