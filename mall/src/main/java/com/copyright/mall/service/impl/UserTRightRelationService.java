package com.copyright.mall.service.impl;

import com.copyright.mall.bean.UserTRightRelation;
import com.copyright.mall.dao.UserTRightRelationMapper;
import com.copyright.mall.service.IUserTRightRelationService;
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
public class UserTRightRelationService implements IUserTRightRelationService {

	private static Logger logger = LoggerFactory.getLogger(UserTRightRelationService.class);

	@Resource
	private UserTRightRelationMapper userTRightRelationMapper;

	@Override
	public UserTRightRelation selectByPrimaryKey(Long id) {
		return userTRightRelationMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return userTRightRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserTRightRelation userTRightRelation) {
		return userTRightRelationMapper.insertSelective(userTRightRelation);
	}

	@Override
	public int updateByPrimaryKeySelective(UserTRightRelation userTRightRelation) {
		return userTRightRelationMapper.updateByPrimaryKeySelective(userTRightRelation);
	}

	@Override
	public Long selectObjectListPageTotal(UserTRightRelation userTRightRelation) {
		return userTRightRelationMapper.selectObjectListPageTotal(userTRightRelation);
	}

	@Override
	public List<UserTRightRelation> selectObjectListPage(UserTRightRelation userTRightRelation) {
		return userTRightRelationMapper.selectObjectListPage(userTRightRelation);
	}

	@Override
	public List<UserTRightRelation> selectByObjectList(UserTRightRelation userTRightRelation){
		return userTRightRelationMapper.selectByObjectList(userTRightRelation);
	}

}
