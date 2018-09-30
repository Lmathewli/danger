package com.life.full.danger.sell.product;

import com.life.full.danger.sell.product.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/9/14 10:10
 * @description
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
