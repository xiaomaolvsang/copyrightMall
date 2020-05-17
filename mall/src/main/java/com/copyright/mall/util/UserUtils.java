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
        List<Long> result = Lists.newArrayList();
        if(userHolder.get().get("roleIds")== null ){
            return result;
        }
        for (Integer roleId : (List<Integer>)userHolder.get().get("roleIds")){
            result.add(Long.valueOf(roleId));
        }
        return result;
    }

    public static List<Long> getShopIds(){
        List<Long> result = Lists.newArrayList();
        if(userHolder.get().get("shopIds")== null ){
            return Lists.newArrayList();
        }
        for (Integer shopId : (List<Integer>)userHolder.get().get("shopIds")){
            result.add(Long.valueOf(shopId));
        }
        return result;
    }

    public static boolean isAdmin(){
        return UserUtils.getRoleIds().contains(1);
    }
}
