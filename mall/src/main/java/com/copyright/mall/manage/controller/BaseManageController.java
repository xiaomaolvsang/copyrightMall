package com.copyright.mall.manage.controller;

import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.JwtService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 10:45
 */
@Slf4j
public abstract class BaseManageController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtService jwtService;

    public Long getUserId() {
        String token = request.getHeader("X-MANAGE-TOKEN");
        String userId = null;
        try {
            userId = jwtService.getClaimFromToken(token).getSubject();
        }catch (Exception e){
            log.warn("解析",e);
        }
        return userId == null ? null :Long.valueOf(userId);
    }

    public List<Long> getShopIds(){
        String token = request.getHeader("X-MANAGE-TOKEN");
        List<Long> shopIds = Lists.newArrayList();
        try {
            List<Integer> intShopIds = (List<Integer>) jwtService.getClaimFromToken(token).get("shop");
            for(Integer intShopId : intShopIds){
                shopIds.add(Long.valueOf(intShopId));
            }
        }catch (Exception e){
            log.warn("解析",e);
        }
        return shopIds == null ? Lists.newArrayList(): shopIds;
    }

    public List<Long> getRoleIds(){
        String token = request.getHeader("X-MANAGE-TOKEN");
        List<Long> roleIds = Lists.newArrayList();
        try {
            List<Integer> intRoleIds = (List<Integer>) jwtService.getClaimFromToken(token).get("roles");
            for(Integer intShopId : intRoleIds){
                roleIds.add(Long.valueOf(intShopId));
            }
        }catch (Exception e){
            log.warn("解析",e);
        }
        return roleIds;
    }

    public Long getMallId() {
        String mallId = request.getHeader("X-Mall-Id");
        return StringUtils.isNotBlank(mallId) ? Long.valueOf(mallId) : -1L;
    }

}
