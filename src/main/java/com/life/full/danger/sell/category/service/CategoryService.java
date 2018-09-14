package com.life.full.danger.sell.category.service;


import com.life.full.danger.sell.category.model.ProductCategory;

import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/9/14 9:54
 * @description
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
