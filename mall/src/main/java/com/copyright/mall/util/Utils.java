package com.copyright.mall.util;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> toStringList(String s){
        s = s.substring(1,s.length() - 1);
        return Arrays.asList(s.split(","));
    }
}
