package com.copyright.mall.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.DataGroupMapper;
import com.copyright.mall.service.IDataGroupService;

import com.copyright.mall.bean.DataGroup;


/**
 *
 * 数据权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class DataGroupService implements IDataGroupService {

	private static Logger logger = LoggerFactory.getLogger(DataGroupService.class);

	@Resource
	private DataGroupMapper dataGroupMapper;

	@Override
	public DataGroup selectByPrimaryKey(Long id) {
		return dataGroupMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return dataGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(DataGroup dataGroup) {
		return dataGroupMapper.insertSelective(dataGroup);
	}

	@Override
	public int updateByPrimaryKeySelective(DataGroup dataGroup) {
		return dataGroupMapper.updateByPrimaryKeySelective(dataGroup);
	}

	@Override
	public Long selectObjectListPageTotal(DataGroup dataGroup) {
		return dataGroupMapper.selectObjectListPageTotal(dataGroup);
	}

	@Override
	public List<DataGroup> selectObjectListPage(DataGroup dataGroup) {
		return dataGroupMapper.selectObjectListPage(dataGroup);
	}

	@Override
	public List<DataGroup> selectByObjectList(DataGroup dataGroup){
		return dataGroupMapper.selectByObjectList(dataGroup);
	}

}
