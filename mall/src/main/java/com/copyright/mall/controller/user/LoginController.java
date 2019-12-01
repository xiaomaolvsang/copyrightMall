package com.copyright.mall.controller.user;

import com.copyright.mall.bean.User;
import com.copyright.mall.domain.dto.user.WeChatUserInfo;
import com.copyright.mall.domain.vo.user.LoginInfoVO;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.IWechatUserService;
import com.copyright.mall.service.JwtService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 22:23
 */
@Api(tags = "登录接口")
@Slf4j
@RestController
@RequestMapping("/v1/user")
public class LoginController {

    @Resource
    private IWechatUserService wechatUserService;

    @Resource
    private JwtService jwtService;

    @Resource
    private IUserService userService;



    @ApiOperation(value = "登录接口")
    @GetMapping("/login")
    public Wrapper<LoginInfoVO> login(String weChatCode){
        WeChatUserInfo weChatUserInfo = null;
        try{
            weChatUserInfo =  wechatUserService.weChatLogin(weChatCode);
        }catch (Exception e){
            log.warn("登录失败",e);
            return WrapMapper.error("登录失败");
        }
        User user= userService.selectByOpenId(weChatUserInfo.getOpenid());
        user.setSessionKey(weChatUserInfo.getSessionKey());
        userService.saveOrUpdate(user);
        LoginInfoVO result = new LoginInfoVO();
        result.setToken(jwtService.generateToken(weChatUserInfo.getUnionId()));
        return WrapMapper.ok(result);
    }

    @ApiOperation(value = "登录接口")
    @GetMapping("/login/encryptedInfo")
    public Wrapper<Boolean> encryptedInfo(String weChatCode){
        return WrapMapper.ok();
    }
}
