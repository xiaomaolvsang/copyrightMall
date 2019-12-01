package com.copyright.mall.service.impl;

import com.copyright.mall.bean.UserAddress;
import com.copyright.mall.dao.UserAddressMapper;
import com.copyright.mall.service.IUserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-30 10:21
 **/
@Service
public class UserAddressServiceImpl implements IUserAddressService {

	private static Logger logger = LoggerFactory.getLogger(UserAddressServiceImpl.class);

	@Resource
	private UserAddressMapper userAddressMapper;

	@Override
	public UserAddress selectByPrimaryKey(Long id) {
		return userAddressMapper.selectByPrimaryKey(id);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int deleteByPrimaryKey(Long id) {
		return userAddressMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int insertSelective(UserAddress userAddress) {
		return userAddressMapper.insertSelective(userAddress);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int updateByPrimaryKeySelective(UserAddress userAddress) {
		return userAddressMapper.updateByPrimaryKeySelective(userAddress);
	}

	@Override
	public Long selectObjectListPageTotal(UserAddress userAddress) {
		return userAddressMapper.selectObjectListPageTotal(userAddress);
	}

	@Override
	public List<UserAddress> selectObjectListPage(UserAddress userAddress) {
		return userAddressMapper.selectObjectListPage(userAddress);
	}

	@Override
	public List<UserAddress> selectByObjectList(UserAddress userAddress){
		return userAddressMapper.selectByObjectList(userAddress);
	}

}
