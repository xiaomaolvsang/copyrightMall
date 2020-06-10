package com.copyright.mall.util;

import com.google.common.base.Strings;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BaseUtil {

    public static String encode(String src){
        if(Strings.isNullOrEmpty(src)){
            return null;
        }
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(src.getBytes());
    }

    public static String decode(String src){
        if(Strings.isNullOrEmpty(src)){
            return null;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(src), StandardCharsets.UTF_8);
    }
}
