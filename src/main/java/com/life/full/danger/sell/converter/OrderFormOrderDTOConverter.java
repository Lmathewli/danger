package com.life.full.danger.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.life.full.danger.sell.form.OrderForm;
import com.life.full.danger.sell.order.dto.OrderDTO;
import com.life.full.danger.sell.order.exception.SellException;
import com.life.full.danger.sell.order.model.OrderDetail;
import com.life.full.danger.sell.order.model.OrderMaster;
import com.life.full.danger.sell.order.orderenum.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHongHui
 * @date 2018/9/26 10:58
 * @description
 */
@Slf4j
public class OrderFormOrderDTOConverter {
    public static OrderDTO convent(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换错误】, string = {}", orderForm.getItems());
            throw new SellException(ResultEnum.param_error);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
