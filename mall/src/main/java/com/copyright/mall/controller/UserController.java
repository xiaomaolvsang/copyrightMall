package com.copyright.mall.controller;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.TUser;
import com.copyright.mall.service.impl.UserServiceImpl;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * TestController
 *
 * @author lijian
 * @version 1.0
 * @date 2019/9/12 5:13 下午
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @RequestMapping("/test1")
    @ResponseBody
    @ControllerErro
    public Wrapper<Boolean> test(){
        TUser tUser = new TUser();
        List<TUser> tUsers = userService.selectByObjectList(tUser);
        for(TUser user : tUsers){
            System.out.println(user.getUserName());
        }
        if(1 == 1) {
            throw new RuntimeException();
        }
        return WrapMapper.ok(true);
    }
}
