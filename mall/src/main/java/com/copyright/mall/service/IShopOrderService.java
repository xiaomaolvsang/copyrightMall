package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.ShopOrder;



/**
 *
 * 商铺订单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IShopOrderService {

	public ShopOrder selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(ShopOrder shopOrder);

	public int updateByPrimaryKeySelective(ShopOrder shopOrder);

	public Long selectObjectListPageTotal(ShopOrder shopOrder);

	public List<ShopOrder> selectObjectListPage(ShopOrder shopOrder);

	public List<ShopOrder> selectByObjectList(ShopOrder shopOrder);

}
