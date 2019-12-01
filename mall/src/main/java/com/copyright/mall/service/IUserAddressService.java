package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.UserAddress;



/**
 * 
 * 
 * @author lijian
 * @date 2019-11-30 10:21
 **/
public interface IUserAddressService {

	public UserAddress selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(UserAddress userAddress);

	public int updateByPrimaryKeySelective(UserAddress userAddress);

	public Long selectObjectListPageTotal(UserAddress userAddress);

	public List<UserAddress> selectObjectListPage(UserAddress userAddress);

	public List<UserAddress> selectByObjectList(UserAddress userAddress);

}
