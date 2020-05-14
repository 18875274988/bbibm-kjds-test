package com.rjkj.cj.bbibm.kjds.order.bo;

import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
@Data
public class Orders {
    private String estimated_shipping_fee;
    private String payment_method;
    private String message_to_seller;
    private String shipping_carrier;
    private String currency;
    private Long create_time;
    private Long pay_time;
    private Long days_to_ship;
    private Long ship_by_date;
    private String tracking_no;
    private String order_status;
    private Long update_time;
    private Recipient_address recipient_address;
    private String escrow_amount;
    private Long goods_to_declare;
    private String total_amount;
    private String service_code;
    private String country;
    private List<Items> items;
    private String ordersn;
    private String buyer_username;

}
