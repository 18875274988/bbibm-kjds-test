package com.rjkj.cj.bbibm.kjds.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
@Data
@TableName("bbibm_kjds_order")
@EqualsAndHashCode
@ApiModel(value = "订单表")
public class OrderPo {
    private String estimatedShippingFee;
    private String paymentMethod;
    private String messageToSeller;
    private String shippingCarrier;
    private String currency;
    private LocalDateTime createTime;
    private LocalDateTime payTime;
    private Long daysToShip;
    private LocalDateTime shipByDate;
    private String trackingNo;
    private String orderStatus;
    private LocalDateTime updateTime;
    private String escrowAmount;
    private String goodsToDeclare;
    private String totalAmount;
    private String serviceCode;
    private String country;
    private String ordersn;
    private String buyerUsername;

}
