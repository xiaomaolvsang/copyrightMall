package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.UserMapper;
import com.copyright.mall.service.IUserService;

import com.copyright.mall.bean.User;


/**
 *
 * 用户表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class UserService implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserMapper userMapper;

	@Override
	public User selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public Long selectObjectListPageTotal(User user) {
		return userMapper.selectObjectListPageTotal(user);
	}

	@Override
	public List<User> selectObjectListPage(User user) {
		return userMapper.selectObjectListPage(user);
	}

	@Override
	public List<User> selectByObjectList(User user){
		return userMapper.selectByObjectList(user);
	}

}
