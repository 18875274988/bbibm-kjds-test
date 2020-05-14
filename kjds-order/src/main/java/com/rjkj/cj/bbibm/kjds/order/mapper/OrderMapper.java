package com.rjkj.cj.bbibm.kjds.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjkj.cj.bbibm.kjds.order.entity.OrderPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
@Mapper
public interface OrderMapper extends BaseMapper<OrderPo> {
     int updeOrderStatus(String ordersn );
}
