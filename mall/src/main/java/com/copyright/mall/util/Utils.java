package com.copyright.mall.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> toStringList(String s){
        if(StringUtils.isBlank(s)){
            return Lists.newArrayList();
        }
        s = s.substring(1,s.length() - 1);
        return Arrays.asList(s.split(","));
    }
}
