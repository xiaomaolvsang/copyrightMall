package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.ItemOrder;



/**
 * 
 * 子订单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ItemOrderMapper {

	public ItemOrder selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(ItemOrder itemOrder);

	public int updateByPrimaryKeySelective(ItemOrder itemOrder);

	public Long selectObjectListPageTotal(ItemOrder itemOrder);

	public List<ItemOrder> selectObjectListPage(ItemOrder itemOrder);

	public List<ItemOrder> selectByObjectList(ItemOrder itemOrder);

}
