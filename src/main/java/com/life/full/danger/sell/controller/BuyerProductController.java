package com.life.full.danger.sell.controller;

import com.life.full.danger.sell.category.model.ProductCategory;
import com.life.full.danger.sell.category.service.CategoryService;
import com.life.full.danger.sell.product.model.ProductInfo;
import com.life.full.danger.sell.product.service.ProductService;
import com.life.full.danger.sell.utils.ResultViewUtil;
import com.life.full.danger.sell.view.ProductInfoView;
import com.life.full.danger.sell.view.ProductView;
import com.life.full.danger.sell.view.ResultView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author LiHongHui
 * @date 2018/9/14 11:36
 * @description
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultView list() {
        // 1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2. 查询类目（一次性查询）
        List<Integer> categoryTypes = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .distinct()
                .collect(Collectors.toList());
        List<ProductCategory>  productCategoryList = categoryService.findByCategoryTypeIn(categoryTypes);
        // 3. 数据拼装
        List<ProductView> productViewList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList) {
            ProductView productView = new ProductView();
            Integer categoryType = productCategory.getCategoryType();
            productView.setCategoryType(categoryType);
            productView.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoView> productInfoViewList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (!Objects.equals(categoryType, productInfo.getCategoryType())) {
                    continue;
                }
                ProductInfoView productInfoView = new ProductInfoView();
                BeanUtils.copyProperties(productInfo, productInfoView);
                productInfoViewList.add(productInfoView);
            }
            productView.setProductInfoViewList(productInfoViewList);
            productViewList.add(productView);
        }
        return ResultViewUtil.success(productViewList);
    }
}
