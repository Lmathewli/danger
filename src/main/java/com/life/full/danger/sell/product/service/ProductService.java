package com.life.full.danger.sell.product.service;

import com.life.full.danger.sell.product.model.dbobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/9/14 10:21
 * @description
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在使用的商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    /**
     * 减库存
     */
}
