package com.copyright.mall.service.impl;

import com.copyright.mall.bean.TUser;
import com.copyright.mall.dao.TUserDao;
import com.copyright.mall.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 * 
 * @author lijian
 * @date 2019-09-12 17:09
 **/
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

	@Resource
	private TUserDao tUserDao;

	@Override
	public TUser selectByPrimaryKey(Integer id) {
		return tUserDao.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {

		return tUserDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(TUser tUser) {
		return tUserDao.insertSelective(tUser);
	}

	@Override
	public int updateByPrimaryKeySelective(TUser tUser) {
		return tUserDao.updateByPrimaryKeySelective(tUser);
	}

	@Override
	public Long selectObjectListPageTotal(TUser tUser) {
		return tUserDao.selectObjectListPageTotal(tUser);
	}

	@Override
	public List<TUser> selectObjectListPage(TUser tUser) {
		return tUserDao.selectObjectListPage(tUser);
	}

	@Override
	public List<TUser> selectByObjectList(TUser tUser){
		return tUserDao.selectByObjectList(tUser);
	}

}
