package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.ItemOrder;
import com.copyright.mall.manage.domain.dto.ItemOrderDetail;


/**
 *
 * 子订单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IItemOrderService {

	public ItemOrder selectByPrimaryKey(Long id);

	public ItemOrder selectShoporderAndItemId(String shopOrderId , Long itemId);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(ItemOrder itemOrder);

	public int updateByPrimaryKeySelective(ItemOrder itemOrder);

	public int updateByShopOrderIdAndItemIdSelective(ItemOrder itemOrder);

	public Long selectObjectListPageTotal(ItemOrder itemOrder);

	public List<ItemOrder> selectObjectListPage(ItemOrder itemOrder);

	public List<ItemOrder> selectByObjectList(ItemOrder itemOrder);

	public List<ItemOrderDetail> selectItemOrderDetail(String shopOrderId);

}
