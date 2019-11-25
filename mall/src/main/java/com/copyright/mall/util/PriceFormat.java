package com.copyright.mall.util;

import java.math.BigDecimal;

/**
 * @author : zhangyuchen
 * @date : 2019/11/25 19:43
 */
public class PriceFormat {
    public static BigDecimal format(Integer integer) {
        if (integer == null){ return null;}
        return BigDecimal.valueOf(integer).divide(new BigDecimal(100));
    }

    public static String formatStr(Integer integer) {
        if (integer == null){ return null;}
        return BigDecimal.valueOf(integer).divide(new BigDecimal(100)).toString();
    }
}
