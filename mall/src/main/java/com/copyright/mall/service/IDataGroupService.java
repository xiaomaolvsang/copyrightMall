package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.DataGroup;



/**
 *
 * 数据权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IDataGroupService {

	public DataGroup selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(DataGroup dataGroup);

	public int updateByPrimaryKeySelective(DataGroup dataGroup);

	public Long selectObjectListPageTotal(DataGroup dataGroup);

	public List<DataGroup> selectObjectListPage(DataGroup dataGroup);

	public List<DataGroup> selectByObjectList(DataGroup dataGroup);

}
