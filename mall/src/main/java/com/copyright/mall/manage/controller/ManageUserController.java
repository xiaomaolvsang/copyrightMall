package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.User;
import com.copyright.mall.bean.UserShopRelation;
import com.copyright.mall.bean.UserTRightRelation;
import com.copyright.mall.manage.domain.dto.LoginParam;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.IUserShopRelationService;
import com.copyright.mall.service.IUserTRightRelationService;
import com.copyright.mall.service.JwtService;
import com.copyright.mall.util.MD5Util;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags = "B端用户相关")
@Slf4j
@RestController
@RequestMapping("/manage/v1/user")
public class ManageUserController extends BaseManageController{

    @Resource
    private IUserService userService;

    @Resource
    private JwtService jwtService;

    @Resource
    private IUserTRightRelationService userTRightRelationService;

    @Resource
    private IUserShopRelationService userShopRelationService;

    @GetMapping("/login")
    @ApiOperation("B端登陆")
    public Wrapper<String> login(@Valid LoginParam loginParam){
        User user = userService.selectByPhoneAndPwd(loginParam.getPhone(),MD5Util.digest(loginParam.getPassword()));
        if(user == null){
            return WrapMapper.error("用户名或密码错误");
        }
        List<UserTRightRelation> userTRightRelations = userTRightRelationService.selectByUserId(user.getId());
        Map<String, Object> claims = new HashMap<>();
        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(user.getId());
        if(!CollectionUtils.isEmpty(userTRightRelations)) {
            claims.put("roles",userTRightRelations.stream().map(UserTRightRelation::getRightId).collect(Collectors.toList()));
        }
        if(!CollectionUtils.isEmpty(userShopRelations)){
            claims.put("shop",userShopRelations.stream().map(UserShopRelation::getShopId).collect(Collectors.toList()));
        }
        claims.put("userId", user.getId());
        return WrapMapper.ok(jwtService.doGenerateToken(claims,user.getId().toString()));
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.digest("1"));
    }

}