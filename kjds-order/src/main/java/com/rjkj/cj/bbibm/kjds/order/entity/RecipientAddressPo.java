package com.rjkj.cj.bbibm.kjds.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
@Data
@TableName("recipient_address")
@EqualsAndHashCode
@ApiModel(value = "订单表")
public class RecipientAddressPo {
    private String ordersn;
    private String phone;
    private String name;
    private String fullAddress;
}
