package com.life.full.danger.sell.converter;

import com.life.full.danger.sell.order.dto.OrderDTO;
import com.life.full.danger.sell.order.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHongHui
 * @date 2018/9/26 10:58
 * @description
 */
public class OrderMasterOrderDTOConverter {
    public static OrderDTO convent(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> convent(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList = orderMasterList.stream()
                .map(orderMaster -> convent(orderMaster))
                .collect(Collectors.toList());
        return orderDTOList;
    }
}
