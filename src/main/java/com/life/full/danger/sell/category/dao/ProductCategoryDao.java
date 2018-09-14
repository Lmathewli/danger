package com.life.full.danger.sell.category.dao;

import com.life.full.danger.sell.category.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/9/13 20:28
 * @description
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据类型获取
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
