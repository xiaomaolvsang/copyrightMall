package com.copyright.mall.controller;

import com.copyright.mall.bean.TUser;
import com.copyright.mall.serviceImpl.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class TestController {

    @Autowired
    private ITUserService itUserServicel;

    @RequestMapping("/test1")
    public void test(){
        TUser tUser = new TUser();
        List<TUser> tUsers = itUserServicel.selectByObjectList(tUser);
        for(TUser user : tUsers){
            System.out.println(user.getUserName());
        }
    }
}
