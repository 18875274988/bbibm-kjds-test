package com.rjkj.cj.bbibm.kjds.order.bo;

import lombok.Data;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
@Data
public class Items {
    private double weight;
    private String item_name;
    private String item_sku;
    private String variation_discounted_price;
    private Long variation_id;
    private String variation_name;
    private boolean is_add_on_deal;
    private Long item_id;
    private String variation_sku;
    private String variation_original_price;
    private boolean is_main_item;
}
