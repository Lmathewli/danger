package com.life.full.danger.sell.order.model;

import com.life.full.danger.sell.order.orderenum.OrderPayStatusEnum;
import com.life.full.danger.sell.order.orderenum.OrderStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiHongHui
 * @date 2018/9/25 15:00
 * @description
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
