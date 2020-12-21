package com.copyright.mall.util;

import java.util.UUID;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 15:01
 */
public class IDUtil {

    public static String generatorID(String prefix){
        return prefix+UUID.randomUUID().toString().replaceAll("-","").substring(0,5)+System.currentTimeMillis()/1000;
    }
}
