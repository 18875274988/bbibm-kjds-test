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
@TableName("bbibm_order_itme")
@EqualsAndHashCode
@ApiModel(value = "订单表")
public class ItemsPo {
    private String ordersn;
    private Double weight;
    private String itemName;
    private String itemSku;
    private Double variationDiscountedPrice;
    private Long variationId;
    private String variationName;
    private String isAddOnDeal;
    private Long itemId;
    private String variationSku;
    private Double variationOriginalPrice;
    private String isMainItem;
}
