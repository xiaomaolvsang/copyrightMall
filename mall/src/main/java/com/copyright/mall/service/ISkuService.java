package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Sku;



/**
 *
 * sku
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ISkuService {

	public Sku selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Sku sku);

	public int updateByPrimaryKeySelective(Sku sku);

	public Long selectObjectListPageTotal(Sku sku);

	public List<Sku> selectObjectListPage(Sku sku);

	public List<Sku> selectByObjectList(Sku sku);

}
