package com.copyright.mall.service.impl;

import com.copyright.mall.bean.UserShopRelation;
import com.copyright.mall.dao.UserShopRelationMapper;
import com.copyright.mall.service.IUserShopRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 12:50
 **/
@Service
public class UserShopRelationService implements IUserShopRelationService {

	private static Logger logger = LoggerFactory.getLogger(UserShopRelationService.class);

	@Resource
	private UserShopRelationMapper userShopRelationMapper;

	@Override
	public UserShopRelation selectByPrimaryKey(Long id) {
		return userShopRelationMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return userShopRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserShopRelation userShopRelation) {
		return userShopRelationMapper.insertSelective(userShopRelation);
	}

	@Override
	public int updateByPrimaryKeySelective(UserShopRelation userShopRelation) {
		return userShopRelationMapper.updateByPrimaryKeySelective(userShopRelation);
	}

	@Override
	public Long selectObjectListPageTotal(UserShopRelation userShopRelation) {
		return userShopRelationMapper.selectObjectListPageTotal(userShopRelation);
	}

	@Override
	public List<UserShopRelation> selectObjectListPage(UserShopRelation userShopRelation) {
		return userShopRelationMapper.selectObjectListPage(userShopRelation);
	}

	@Override
	public List<UserShopRelation> selectByObjectList(UserShopRelation userShopRelation){
		return userShopRelationMapper.selectByObjectList(userShopRelation);
	}

	@Override
	public List<UserShopRelation> selectByUserId(Long userId) {
		UserShopRelation userShopRelation = new UserShopRelation();
		userShopRelation.setUserId(userId);
		return userShopRelationMapper.selectByObjectList(userShopRelation);
	}

	@Override
	public List<UserShopRelation> selectByShopId(Long shopId) {
		UserShopRelation userShopRelation = new UserShopRelation();
		userShopRelation.setShopId(shopId);
		return userShopRelationMapper.selectByObjectList(userShopRelation);
	}

}
