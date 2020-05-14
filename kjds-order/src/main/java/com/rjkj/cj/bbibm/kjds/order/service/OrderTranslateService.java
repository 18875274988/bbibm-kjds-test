package com.rjkj.cj.bbibm.kjds.order.service;

import com.rjkj.cf.common.core.util.R;
import com.rjkj.cj.bbibm.kjds.order.bo.OrderTranslateBo;

/**
 * 描述：订单同步
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
public interface OrderTranslateService {
    /**
     * 同步订单子功能
     * @param orderTranslateBo
     * @return
     */
    R OrderTranslate(OrderTranslateBo orderTranslateBo);

    /**
     * 订单同步功能循环调用子功能
     * @return
     */
    R OrderTranslates();
}
