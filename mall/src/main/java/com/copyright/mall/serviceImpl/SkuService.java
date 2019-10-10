package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.SkuMapper;
import com.copyright.mall.service.ISkuService;

import com.copyright.mall.bean.Sku;


/**
 *
 * sku
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class SkuService implements ISkuService {

	private static Logger logger = LoggerFactory.getLogger(SkuService.class);

	@Resource
	private SkuMapper skuMapper;

	@Override
	public Sku selectByPrimaryKey(Long id) {
		return skuMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return skuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Sku sku) {
		return skuMapper.insertSelective(sku);
	}

	@Override
	public int updateByPrimaryKeySelective(Sku sku) {
		return skuMapper.updateByPrimaryKeySelective(sku);
	}

	@Override
	public Long selectObjectListPageTotal(Sku sku) {
		return skuMapper.selectObjectListPageTotal(sku);
	}

	@Override
	public List<Sku> selectObjectListPage(Sku sku) {
		return skuMapper.selectObjectListPage(sku);
	}

	@Override
	public List<Sku> selectByObjectList(Sku sku){
		return skuMapper.selectByObjectList(sku);
	}

}
