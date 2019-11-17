package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.ShopOrderMapper;
import com.copyright.mall.service.IShopOrderService;

import com.copyright.mall.bean.ShopOrder;


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

	@Override
	public ShopOrder selectByPrimaryKey(Long id) {
		return shopOrderMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return shopOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ShopOrder shopOrder) {
		return shopOrderMapper.insertSelective(shopOrder);
	}

	@Override
	public int updateByPrimaryKeySelective(ShopOrder shopOrder) {
		return shopOrderMapper.updateByPrimaryKeySelective(shopOrder);
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

}
