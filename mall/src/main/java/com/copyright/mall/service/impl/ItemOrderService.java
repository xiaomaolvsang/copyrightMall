package com.copyright.mall.service.impl;

import java.util.List;

import com.copyright.mall.manage.domain.dto.ItemOrderDetail;
import com.copyright.mall.service.IItemOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.ItemOrderMapper;
import com.copyright.mall.service.IItemOrderService;

import com.copyright.mall.bean.ItemOrder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 *
 * 子订单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class ItemOrderService implements IItemOrderService {

	private static Logger logger = LoggerFactory.getLogger(ItemOrderService.class);

	@Resource
	private ItemOrderMapper itemOrderMapper;

	@Override
	public ItemOrder selectByPrimaryKey(Long id) {
		return itemOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public ItemOrder selectShoporderAndItemId(String shopOrderId , Long itemId) {
		ItemOrder itemOrder = new ItemOrder();
		itemOrder.setShopOrderId(shopOrderId);
		itemOrder.setItemId(itemId);
		List<ItemOrder> itemOrders = itemOrderMapper.selectByObjectList(itemOrder);
		return CollectionUtils.isEmpty(itemOrders)?null:itemOrders.get(0);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {

		return itemOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int insertSelective(ItemOrder itemOrder) {
		return itemOrderMapper.insertSelective(itemOrder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int updateByPrimaryKeySelective(ItemOrder itemOrder) {
		return itemOrderMapper.updateByPrimaryKeySelective(itemOrder);
	}

	@Override
	public int updateByShopOrderIdAndItemIdSelective(ItemOrder itemOrder) {
		return itemOrderMapper.updateByShopOrderIdAndItemIdSelective(itemOrder);
	}

	@Override
	public Long selectObjectListPageTotal(ItemOrder itemOrder) {
		return itemOrderMapper.selectObjectListPageTotal(itemOrder);
	}

	@Override
	public List<ItemOrder> selectObjectListPage(ItemOrder itemOrder) {
		return itemOrderMapper.selectObjectListPage(itemOrder);
	}

	@Override
	public List<ItemOrder> selectByObjectList(ItemOrder itemOrder){
		return itemOrderMapper.selectByObjectList(itemOrder);
	}

	@Override
	public List<ItemOrderDetail> selectItemOrderDetail(String shopOrderId) {
		return itemOrderMapper.selectItemOrderDetail(shopOrderId);
	}

}
