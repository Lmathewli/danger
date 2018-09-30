package com.life.full.danger.sell.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.life.full.danger.sell.order.model.OrderDetail;
import com.life.full.danger.sell.order.orderenum.OrderPayStatusEnum;
import com.life.full.danger.sell.order.orderenum.OrderStatusEnum;
import com.life.full.danger.sell.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LiHongHui
 * @date 2018/9/25 16:40
 * @description
 * //@JsonInclude(JsonInclude.Include.NON_NULL)
 */
@Data
public class OrderDTO {

    private String orderId;
    /**
     * 买家姓名
     */
    private String buyerName;
    /**
     * 买家手机号
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openId
     */
    private String buyerOpenid;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认为新订单
     */
    private Integer orderStatus = OrderStatusEnum.NEW_ORDER.getCode();

    /**
     * 支付状态，默认为0未支付
     */
    private Integer payStatus = OrderPayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetailList;
}
