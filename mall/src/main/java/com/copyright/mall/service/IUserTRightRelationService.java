package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.UserTRightRelation;



/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 12:50
 **/
public interface IUserTRightRelationService {

	public UserTRightRelation selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(UserTRightRelation userTRightRelation);

	public int updateByPrimaryKeySelective(UserTRightRelation userTRightRelation);

	public Long selectObjectListPageTotal(UserTRightRelation userTRightRelation);

	public List<UserTRightRelation> selectObjectListPage(UserTRightRelation userTRightRelation);

	public List<UserTRightRelation> selectByObjectList(UserTRightRelation userTRightRelation);

	List<UserTRightRelation> selectByUserId(Long userId);

}
