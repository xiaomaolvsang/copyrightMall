package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Kv;
import com.copyright.mall.dao.KvMapper;
import com.copyright.mall.manage.domain.dto.QueryBlobListParam;
import com.copyright.mall.service.IKvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 14:36
 **/
@Service
public class KvService implements IKvService {

	private static Logger logger = LoggerFactory.getLogger(KvService.class);

	@Resource
	private KvMapper kvMapper;

	@Override
	public Kv selectByPrimaryKey(Integer id) {
		return kvMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {

		return kvMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Kv kv) {
		return kvMapper.insertSelective(kv);
	}

	@Override
	public int updateByPrimaryKeySelective(Kv kv) {
		return kvMapper.updateByPrimaryKeySelective(kv);
	}

	@Override
	public Long selectObjectListPageTotal(Kv kv) {
		return kvMapper.selectObjectListPageTotal(kv);
	}

	@Override
	public List<Kv> selectObjectListPage(Kv kv) {
		return kvMapper.selectObjectListPage(kv);
	}

	@Override
	public List<Kv> selectByObjectList(Kv kv){
		return kvMapper.selectByObjectList(kv);
	}

	@Override
	public List<Kv> queryKvList(QueryBlobListParam queryBlobListParam) {
		return kvMapper.queryKVList(queryBlobListParam);
	}

}
