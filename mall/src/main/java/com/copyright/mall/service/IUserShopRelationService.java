package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.UserShopRelation;



/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 12:50
 **/
public interface IUserShopRelationService {

	public UserShopRelation selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(UserShopRelation userShopRelation);

	public int updateByPrimaryKeySelective(UserShopRelation userShopRelation);

	public Long selectObjectListPageTotal(UserShopRelation userShopRelation);

	public List<UserShopRelation> selectObjectListPage(UserShopRelation userShopRelation);

	public List<UserShopRelation> selectByObjectList(UserShopRelation userShopRelation);

}
