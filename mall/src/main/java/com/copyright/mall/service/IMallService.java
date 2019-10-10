package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Mall;



/**
 *
 * 商城表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IMallService {

	public Mall selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Mall mall);

	public int updateByPrimaryKeySelective(Mall mall);

	public Long selectObjectListPageTotal(Mall mall);

	public List<Mall> selectObjectListPage(Mall mall);

	public List<Mall> selectByObjectList(Mall mall);

}
