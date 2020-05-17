package com.copyright.mall.service.impl;

import com.copyright.mall.bean.ItemOrder;
import com.copyright.mall.bean.ShopOrder;
import com.copyright.mall.dao.ShopOrderMapper;
import com.copyright.mall.manage.domain.dto.ItemOrderDetail;
import com.copyright.mall.manage.domain.dto.QueryOrderListParam;
import com.copyright.mall.manage.domain.dto.ShopOrderDetail;
import com.copyright.mall.service.IShopOrderService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * 商铺订单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class ShopOrderService implements IShopOrderService {

	private static Logger logger = LoggerFactory.getLogger(ShopOrderService.class);

	@Resource
	private ShopOrderMapper shopOrderMapper;

	@Resource
	private ItemOrderService itemOrderService;

	@Override
	public ShopOrder selectByPrimaryKey(Long id) {
		return shopOrderMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return shopOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int insertSelective(ShopOrder shopOrder) {
		return shopOrderMapper.insertSelective(shopOrder);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int updateByPrimaryKeySelective(ShopOrder shopOrder) {
		return shopOrderMapper.updateByPrimaryKeySelective(shopOrder);
	}

	@Override
	public int updateByShopOrderId(ShopOrder shopOrder) {
		return shopOrderMapper.updateByShopOrderIdSelective(shopOrder);
	}

	@Override
	public Long selectObjectListPageTotal(ShopOrder shopOrder) {
		return shopOrderMapper.selectObjectListPageTotal(shopOrder);
	}

	@Override
	public List<ShopOrder> selectObjectListPage(ShopOrder shopOrder) {
		return shopOrderMapper.selectObjectListPage(shopOrder);
	}

	@Override
	public List<ShopOrder> selectByObjectList(ShopOrder shopOrder){
		return shopOrderMapper.selectByObjectList(shopOrder);
	}

	@Override
	public ShopOrder selectByShopOrderId(String shopOrderId){
		ShopOrder shopOrder = new ShopOrder();
		shopOrder.setShopOrderId(shopOrderId);
		List<ShopOrder> shopOrders = shopOrderMapper.selectByObjectList(shopOrder);
		return CollectionUtils.isEmpty(shopOrders)?null:shopOrders.get(0);
	}

	@Override
	public int modifyByShopOrderId(ShopOrder shopOrder) {
		return shopOrderMapper.updateByShopOrderIdSelective(shopOrder);
	}

	@Override
	public List<ShopOrderDetail> selectShopOrder(QueryOrderListParam queryOrderListParam) {
		List<ShopOrderDetail> shopOrders = shopOrderMapper.selectShopOrder(queryOrderListParam);
		for(ShopOrderDetail shopOrder : shopOrders){
			List<ShopOrderDetail.ItemOrder> shopItemOrders = Lists.newArrayList();
			ItemOrder queryOrder = new ItemOrder();
			queryOrder.setShopOrderId(shopOrder.getShopOrderId());
			List<ItemOrderDetail> itemOrders = itemOrderService.selectItemOrderDetail(shopOrder.getShopOrderId());
			for(ItemOrderDetail itemOrder : itemOrders){
				ShopOrderDetail.ItemOrder shopItemOrder = new ShopOrderDetail.ItemOrder();
				shopItemOrder.setItemOrderId(itemOrder.getItemOrderId());
				shopItemOrder.setNum(itemOrder.getNum());
				shopItemOrder.setProductName(itemOrder.getProductName());
				shopItemOrder.setProductPrice(itemOrder.getProductPrice());
				shopItemOrder.setSkuId(itemOrder.getSkuId());
				shopItemOrder.setImage(itemOrder.getImage());
				shopItemOrder.setItemType(itemOrder.getItemType());
				shopItemOrders.add(shopItemOrder);
			}
			shopOrder.setItemOrders(shopItemOrders);
		}
		return shopOrders;
	}
}
