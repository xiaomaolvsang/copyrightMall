package com.copyright.mall.dao;

import com.copyright.mall.bean.Classification;

import java.util.List;



/**
 *
 *
 * @author lijian
 * @date 2019-11-20 15:16
 **/
public interface ClassificationMapper {

	public Classification selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Classification classification);

	public int updateByPrimaryKeySelective(Classification classification);

	public Long selectObjectListPageTotal(Classification classification);

	public List<Classification> selectObjectListPage(Classification classification);

	public List<Classification> selectByObjectList(Classification classification);

}
