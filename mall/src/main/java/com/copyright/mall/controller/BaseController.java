package com.copyright.mall.controller;

import com.copyright.mall.bean.User;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 10:45
 */
@Slf4j
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtService jwtService;

    @Resource
    private IUserService userService;

    public Long getRealUserId(){
        String token = request.getHeader("X-Mall-TOKEN");
        User user = null;
        String userOpenId = null;
        try {
            userOpenId = jwtService.getClaimFromToken(token).getSubject();
            user =  userService.selectByOpenId(userOpenId);
        }catch (Exception e){
            log.warn("解析",e);
        }
        if(user==null){
            log.warn("用户数据不完整=>openId{}",userOpenId);
            throw new BusinessException("用户数据不完整");
        }
        return user.getId();
    }

    public Long getUserId() {
        String token = request.getHeader("X-Mall-TOKEN");
        User user = null;
        String userOpenId = null;
        try {
            userOpenId = jwtService.getClaimFromToken(token).getSubject();
            user =  userService.selectByPhone(this.getUserPhone());
        }catch (Exception e){
            log.warn("解析",e);
        }
        if(user==null){
            log.warn("用户数据不完整=>openId{}",userOpenId);
            throw new BusinessException("用户数据不完整");
        }
        return user.getId();
    }

    public String getUserPhone() {
        String token = request.getHeader("X-Mall-TOKEN");
        User user = null;
        String userOpenId = null;
        try {
            userOpenId = jwtService.getClaimFromToken(token).getSubject();
            user =  userService.selectByOpenId(userOpenId);
        }catch (Exception e){
            log.warn("解析",e);
        }
        if(user==null){
            log.warn("用户数据不完整=>openId{}",userOpenId);
            throw new BusinessException("用户数据不完整");
        }
        return user.getPhone();
    }

    public Long getMallId() {
        String mallId = request.getHeader("X-Mall-Id");
        return StringUtils.isNotBlank(mallId) ? Long.valueOf(mallId) : -1L;
    }


    public String getOpenId(){
        return  jwtService.getClaimFromToken(request.getHeader("X-Mall-TOKEN")).getSubject();
    }
}
