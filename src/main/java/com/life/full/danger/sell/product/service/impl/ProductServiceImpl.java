package com.life.full.danger.sell.product.service.impl;

import com.life.full.danger.sell.order.dto.CartDTO;
import com.life.full.danger.sell.order.exception.SellException;
import com.life.full.danger.sell.order.orderenum.ResultEnum;
import com.life.full.danger.sell.product.ProductInfoDao;
import com.life.full.danger.sell.product.enums.ProductStatusEnum;
import com.life.full.danger.sell.product.model.ProductInfo;
import com.life.full.danger.sell.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author LiHongHui
 * @date 2018/9/14 10:24
 * @description
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById(productId).get();
    }

    /**
     * 查询所有在使用的商品
     *
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    /**
     * 加库存
     *
     * @param cartDTOList
     */
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoDao.findById(cartDTO.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new SellException(ResultEnum.product_not_exist);
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

    /**
     * 减库存
     *
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.product_not_exist);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.product_stock_error);
            }
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }

    }
}
