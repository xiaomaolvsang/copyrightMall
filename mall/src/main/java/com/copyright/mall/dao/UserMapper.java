package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.User;



/**
 * 
 * 用户表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface UserMapper {

	public User selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(User user);

	public int updateByPrimaryKeySelective(User user);

	public Long selectObjectListPageTotal(User user);

	public List<User> selectObjectListPage(User user);

	public List<User> selectByObjectList(User user);

}
