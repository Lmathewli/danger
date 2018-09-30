package com.life.full.danger.sell.controller;

import com.life.full.danger.sell.converter.OrderFormOrderDTOConverter;
import com.life.full.danger.sell.form.OrderForm;
import com.life.full.danger.sell.order.dto.OrderDTO;
import com.life.full.danger.sell.order.exception.SellException;
import com.life.full.danger.sell.order.orderenum.ResultEnum;
import com.life.full.danger.sell.order.service.BuyerService;
import com.life.full.danger.sell.order.service.OrderService;
import com.life.full.danger.sell.utils.ResultViewUtil;
import com.life.full.danger.sell.view.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiHongHui
 * @date 2018/9/14 11:36
 * @description
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @RequestMapping("/create")
    public ResultView<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        // 校验表单
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确， orderForm = {}", orderForm);
            throw new SellException(ResultEnum.param_error.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormOrderDTOConverter.convent(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.cart_empty);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultViewUtil.success(map);
    }

    /**
     * 订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/list")
    public ResultView<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid 为空");
            throw new SellException(ResultEnum.param_error);
        }
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ResultViewUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @RequestMapping("/detail")
    public ResultView<OrderDTO> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        // TODO 不安全的做法，改进
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultViewUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @RequestMapping("/cancel")
    public ResultView<OrderDTO> cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        // TODO 不安全的做法，改进
        buyerService.cancelOrder(openid, orderId);
        return ResultViewUtil.success();
    }

}
