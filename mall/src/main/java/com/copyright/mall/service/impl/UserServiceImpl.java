package com.copyright.mall.service.impl;

import com.copyright.mall.bean.User;
import com.copyright.mall.dao.UserMapper;
import com.copyright.mall.service.IUserService;
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
    public void saveOrUpdate(User user) {
        if(user.getId()==null){
            userMapper.insertSelective(user);
        }else{
            userMapper.updateByPrimaryKeySelective(user);
        }
    }
}
