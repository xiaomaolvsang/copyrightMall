package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.MallMapper;
import com.copyright.mall.service.IMallService;

import com.copyright.mall.bean.Mall;


/**
 *
 * 商城表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class MallService implements IMallService {

	private static Logger logger = LoggerFactory.getLogger(MallService.class);

	@Resource
	private MallMapper mallMapper;

	@Override
	public Mall selectByPrimaryKey(Long id) {
		return mallMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return mallMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Mall mall) {
		return mallMapper.insertSelective(mall);
	}

	@Override
	public int updateByPrimaryKeySelective(Mall mall) {
		return mallMapper.updateByPrimaryKeySelective(mall);
	}

	@Override
	public Long selectObjectListPageTotal(Mall mall) {
		return mallMapper.selectObjectListPageTotal(mall);
	}

	@Override
	public List<Mall> selectObjectListPage(Mall mall) {
		return mallMapper.selectObjectListPage(mall);
	}

	@Override
	public List<Mall> selectByObjectList(Mall mall){
		return mallMapper.selectByObjectList(mall);
	}

}
