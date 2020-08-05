package com.copyright.mall.service.impl;

import com.copyright.mall.bean.User;
import com.copyright.mall.dao.UserMapper;
import com.copyright.mall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/12/1 11:29
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectByOpenId(String openId) {
        User parm = new User();
        parm.setOpenId(openId);
        List<User> users =  userMapper.selectByObjectList(parm);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }

    @Override
    public User selectByUserId(Long userId) {
        User parm = new User();
        parm.setId(userId);
        List<User> users =  userMapper.selectByObjectList(parm);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }

    @Override
    public void saveOrUpdate(User user) {
        if(user.getId()==null){
            userMapper.insertSelective(user);
        }else{
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public User selectByPhoneAndPwd(String phone , String  pwd) {
        if(StringUtils.isBlank(pwd)){
            return null;
        }
        User queryParam = new User();
        queryParam.setPhone(phone);
        queryParam.setPassword(pwd);
        List<User> users = userMapper.selectByObjectList(queryParam);
        return CollectionUtils.isEmpty(users)? null : users.get(0);
    }

    @Override
    public User selectByPhone(String phone) {
        User parm = new User();
        if(StringUtils.isEmpty(phone)){
            return null;
        }
        parm.setPhone(phone);
        List<User> users =  userMapper.selectByObjectList(parm);
        if(CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }
}
