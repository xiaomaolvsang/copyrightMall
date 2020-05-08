package com.copyright.mall.service.impl;

import com.copyright.mall.bean.TRight;
import com.copyright.mall.dao.TRightMapper;
import com.copyright.mall.service.ITRightService;
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
public class TRightService implements ITRightService {

	private static Logger logger = LoggerFactory.getLogger(TRightService.class);

	@Resource
	private TRightMapper tRightMapper;

	@Override
	public TRight selectByPrimaryKey(Long id) {
		return tRightMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return tRightMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(TRight tRight) {
		return tRightMapper.insertSelective(tRight);
	}

	@Override
	public int updateByPrimaryKeySelective(TRight tRight) {
		return tRightMapper.updateByPrimaryKeySelective(tRight);
	}

	@Override
	public Long selectObjectListPageTotal(TRight tRight) {
		return tRightMapper.selectObjectListPageTotal(tRight);
	}

	@Override
	public List<TRight> selectObjectListPage(TRight tRight) {
		return tRightMapper.selectObjectListPage(tRight);
	}

	@Override
	public List<TRight> selectByObjectList(TRight tRight){
		return tRightMapper.selectByObjectList(tRight);
	}

}
