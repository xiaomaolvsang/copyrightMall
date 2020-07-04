package com.copyright.mall.service;

import com.copyright.mall.bean.Copyright;

import java.util.List;



/**
 * 
 * 版权表
 * @author lijian
 * @date 2020-06-10 10:35
 **/
public interface ICopyrightService {

	public Copyright selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Copyright copyright);

	public int updateByPrimaryKeySelective(Copyright copyright);

	public Long selectObjectListPageTotal(Copyright copyright);

	public List<Copyright> selectObjectListPage(Copyright copyright);

	public List<Copyright> selectByObjectList(Copyright copyright);

	public Copyright selectByCopyRightId(String copyrightID);
}
