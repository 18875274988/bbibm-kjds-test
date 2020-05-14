package com.rjkj.cj.bbibm.kjds.order.controller;

import com.rjkj.cf.common.core.util.R;
import com.rjkj.cj.bbibm.kjds.order.bo.OrderTranslateBo;
import com.rjkj.cj.bbibm.kjds.order.service.OrderTranslateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
@RestController
@AllArgsConstructor
public class TestController {
    private final OrderTranslateService orderTranslateService;
    @RequestMapping("test")
    public R test(){
        return orderTranslateService.OrderTranslates();
    }
}
