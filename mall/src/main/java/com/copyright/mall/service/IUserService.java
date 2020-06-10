package com.copyright.mall.service;

import com.copyright.mall.bean.User;

/**
 * @author : zhangyuchen
 * @date : 2019/12/1 11:28
 */
public interface IUserService {


    User selectByOpenId(String openId);

    User selectByUserId(Long openId);

    void saveOrUpdate(User user);

    User selectByPhoneAndPwd(String phone ,String pwd);

    public User selectByPhone(String phone);

}
