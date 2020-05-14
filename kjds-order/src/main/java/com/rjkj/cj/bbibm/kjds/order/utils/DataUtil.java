package com.rjkj.cj.bbibm.kjds.order.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 描述：
 *
 * @author PuYinsheng
 * @date 2020/5/13
 * @copyright 2020 [图片]www.tydic.com Inc. All rights reserved.
 **/
public class DataUtil {
    public static LocalDateTime LongToDate(Long dateLong){
        if (dateLong==null){
            return null;
        }else {
            Instant instant = Instant.ofEpochMilli(dateLong);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }
}
