package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Shop;



/**
 * 
 * 商铺表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ShopMapper {

	public Shop selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Shop shop);

	public int updateByPrimaryKeySelective(Shop shop);

	public Long selectObjectListPageTotal(Shop shop);

	public List<Shop> selectObjectListPage(Shop shop);

	public List<Shop> selectByObjectList(Shop shop);

}
