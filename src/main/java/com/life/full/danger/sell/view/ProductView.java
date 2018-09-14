package com.life.full.danger.sell.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品包含类目
 * @author LiHongHui
 * @date 2018/9/14 12:00
 * @description
 */
@Data
public class ProductView {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("food")
    List<ProductInfoView> productInfoViewList;
}
