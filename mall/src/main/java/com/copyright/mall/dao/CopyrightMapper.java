package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Copyright;



/**
 * 
 * 版权表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface CopyrightMapper {

	public Copyright selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Copyright copyright);

	public int updateByPrimaryKeySelective(Copyright copyright);

	public Long selectObjectListPageTotal(Copyright copyright);

	public List<Copyright> selectObjectListPage(Copyright copyright);

	public List<Copyright> selectByObjectList(Copyright copyright);

}
