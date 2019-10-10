package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.TUser;



/**
 *
 *
 * @author lijian
 * @date 2019-09-12 17:09
 **/
public interface TUserDao {

	public TUser selectByPrimaryKey(Integer id);

	public int deleteByPrimaryKey(Integer id);

	public int insertSelective(TUser tUser);

	public int updateByPrimaryKeySelective(TUser tUser);

	public Long selectObjectListPageTotal(TUser tUser);

	public List<TUser> selectObjectListPage(TUser tUser);

	public List<TUser> selectByObjectList(TUser tUser);


}
