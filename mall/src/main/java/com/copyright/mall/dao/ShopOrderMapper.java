package com.copyright.mall.dao;

import java.util.List;

import com.copyright.mall.bean.ShopOrder;
import com.copyright.mall.manage.domain.dto.QueryOrderListParam;
import com.copyright.mall.manage.domain.dto.ShopOrderDetail;


/**
 * 
 * 商铺订单
 * @author lijian
 * @date 2019-11-27 15:25
 **/
public interface ShopOrderMapper {

	public ShopOrder selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(ShopOrder shopOrder);

	public int updateByPrimaryKeySelective(ShopOrder shopOrder);

	public int updateByShopOrderIdSelective(ShopOrder shopOrder);

	public Long selectObjectListPageTotal(ShopOrder shopOrder);

	public List<ShopOrder> selectObjectListPage(ShopOrder shopOrder);

	public List<ShopOrder> selectByObjectList(ShopOrder shopOrder);

	List<ShopOrderDetail> selectOrderDetailList(QueryOrderListParam queryOrderListParam);

	List<ShopOrderDetail> selectShopOrder(QueryOrderListParam queryOrderListParam);

}
