package com.copyright.mall.util;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class UserUtils {
    private static final ThreadLocal<Map<String,Object>> userHolder = new ThreadLocal<>();

    public static void setUserId(Long userID){
        if(userHolder.get()==null){
            userHolder.set(Maps.newHashMap());
        }
        userHolder.get().put("userId",userID);
    }

    public static void setRoleIds(List<Long> roleIds){
        if(userHolder.get()==null){
            userHolder.set(Maps.newHashMap());
        }
        userHolder.get().put("roleIds",roleIds);
    }

    public static void setShopIds(List<Long> shopIds){
        if(userHolder.get()==null){
            userHolder.set(Maps.newHashMap());
        }
        userHolder.get().put("shopIds",shopIds);
    }

    public static Long getUserId(){
        if(userHolder.get().get("userId")== null ){
            return null;
        }
        return (Long) userHolder.get().get("userId");
    }

    public static List<Long> getRoleIds(){
        if(userHolder.get().get("roleIds")== null ){
            return Lists.newArrayList();
        }
        return (List<Long>) userHolder.get().get("roleIds");
    }

    public static List<Long> getShopIds(){
        if(userHolder.get().get("shopIds")== null ){
            return Lists.newArrayList();
        }
        return (List<Long>) userHolder.get().get("shopIds");
    }
}
