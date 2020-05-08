package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.TRight;



/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 12:50
 **/
public interface TRightMapper {

	public TRight selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(TRight tRight);

	public int updateByPrimaryKeySelective(TRight tRight);

	public Long selectObjectListPageTotal(TRight tRight);

	public List<TRight> selectObjectListPage(TRight tRight);

	public List<TRight> selectByObjectList(TRight tRight);

}
