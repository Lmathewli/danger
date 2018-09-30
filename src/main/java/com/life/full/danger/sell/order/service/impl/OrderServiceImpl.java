package com.life.full.danger.sell.order.service.impl;

import com.life.full.danger.sell.converter.OrderMasterOrderDTOConverter;
import com.life.full.danger.sell.order.dao.OrderDao;
import com.life.full.danger.sell.order.dao.OrderDetailDao;
import com.life.full.danger.sell.order.dto.CartDTO;
import com.life.full.danger.sell.order.dto.OrderDTO;
import com.life.full.danger.sell.order.exception.SellException;
import com.life.full.danger.sell.order.model.OrderDetail;
import com.life.full.danger.sell.order.model.OrderMaster;
import com.life.full.danger.sell.order.orderenum.OrderPayStatusEnum;
import com.life.full.danger.sell.order.orderenum.OrderStatusEnum;
import com.life.full.danger.sell.order.orderenum.ResultEnum;
import com.life.full.danger.sell.order.service.OrderService;
import com.life.full.danger.sell.product.model.ProductInfo;
import com.life.full.danger.sell.product.service.ProductService;
import com.life.full.danger.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author LiHongHui
 * @date 2018/9/25 16:51
 * @description
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductService productService;

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        // 查询商品（数量，价格）
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail detail : orderDetailList) {
            ProductInfo productInfo = productService.findOne(detail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.product_not_exist);
            }
            // 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(detail.getProductQuantity()))
                    .add(orderAmount);
            // 订单详情入数据库
            detail.setDetailId(KeyUtil.genUniqueKey());
            detail.setOrderId(orderId);
            // 将productInfo里面的属性copy到detail里面
            BeanUtils.copyProperties(productInfo, detail);
            orderDetailDao.save(detail);
        }
        // 写入订单数据库（orderMaster 和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderDao.save(orderMaster);
        // 扣库存
        List<CartDTO> cartDTOList = orderDetailList.stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return OrderMasterOrderDTOConverter.convent(orderMaster);
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        Optional<OrderMaster> orderMasterOptional = orderDao.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new SellException(ResultEnum.order_not_exist);
        }
        OrderMaster orderMaster = orderMasterOptional.get();
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.order_detail_not_exist);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询订单列表
     *
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterList = orderDao.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMasterOrderDTOConverter.convent(orderMasterList.getContent());
        Page<OrderDTO> pageList = new PageImpl<>(orderDTOList, pageable, orderMasterList.getTotalElements());
        return pageList;
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        // 判断订单的状态
        Optional<OrderMaster> orderMasterOptional = orderDao.findById(orderDTO.getOrderId());
        if (!orderMasterOptional.isPresent()) {
            log.error("订单不存在，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.order_not_exist);
        }
        OrderMaster orderMaster = orderMasterOptional.get();
        Integer status = orderMaster.getOrderStatus();
        if (!Objects.equals(status, OrderStatusEnum.NEW_ORDER.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={}, orderStatus={}", orderMaster.getOrderId(), status);
            throw new SellException(ResultEnum.order_status_error);
        }
        // 修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.cancel.getCode());
        OrderMaster updateResult = orderDao.save(orderMaster);
        if (updateResult == null) {
            log.error("【更新失败】，order={}", orderDTO);
            throw new SellException(ResultEnum.order_update_error);
        }
        // 返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情， orderDTO = {}", orderDTO);
            throw new SellException(ResultEnum.order_detail_not_exist);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        // 退款，如已支付，需要退款
        if (Objects.equals(orderDTO.getPayStatus(), OrderPayStatusEnum.SUCCESS.getCode())) {
            // TODO
        }
        return OrderMasterOrderDTOConverter.convent(updateResult);
    }

    /**
     * 完成订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        Optional<OrderMaster> orderMasterOptional = orderDao.findById(orderDTO.getOrderId());
        if (!orderMasterOptional.isPresent()) {
            log.error("订单不存在，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.order_not_exist);
        }
        OrderMaster orderMaster = orderMasterOptional.get();
        Integer status = orderMaster.getOrderStatus();
        if (!Objects.equals(status, OrderStatusEnum.NEW_ORDER.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={}, orderStatus={}", orderMaster.getOrderId(), status);
            throw new SellException(ResultEnum.order_status_error);
        }
        // 修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster result = orderDao.save(orderMaster);
        if (result == null) {
            log.error("【更新失败】，order={}", orderDTO);
            throw new SellException(ResultEnum.order_update_error);
        }
        return OrderMasterOrderDTOConverter.convent(result);
    }

    /**
     * 支付订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态,判断支付状态
        Optional<OrderMaster> orderMasterOptional = orderDao.findById(orderDTO.getOrderId());
        if (!orderMasterOptional.isPresent()) {
            log.error("订单不存在，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.order_not_exist);
        }
        OrderMaster orderMaster = orderMasterOptional.get();
        Integer status = orderMaster.getOrderStatus();
        if (!Objects.equals(status, OrderStatusEnum.NEW_ORDER.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId={}, orderStatus={}", orderMaster.getOrderId(), status);
            throw new SellException(ResultEnum.order_status_error);
        }
        Integer payStatus = orderMaster.getPayStatus();
        if (!Objects.equals(payStatus, OrderPayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.order_pay_status_error);
        }
        // 修改支付状态
        orderMaster.setPayStatus(OrderPayStatusEnum.SUCCESS.getCode());
        OrderMaster result = orderDao.save(orderMaster);
        if (result == null) {
            log.error("【订单支付完成】更新失败，orderMaster={}", result);
            throw new SellException(ResultEnum.order_update_error);
        }
        return OrderMasterOrderDTOConverter.convent(result);
    }
}
