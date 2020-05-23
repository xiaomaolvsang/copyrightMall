package com.copyright.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private static final String formatStr = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(date);
    }
}
