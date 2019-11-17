package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.ShopMapper;
import com.copyright.mall.service.IShopService;

import com.copyright.mall.bean.Shop;


/**
 *
 * 商铺表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class ShopService implements IShopService {

	private static Logger logger = LoggerFactory.getLogger(ShopService.class);

	@Resource
	private ShopMapper shopMapper;

	@Override
	public Shop selectByPrimaryKey(Long id) {
		return shopMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return shopMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Shop shop) {
		return shopMapper.insertSelective(shop);
	}

	@Override
	public int updateByPrimaryKeySelective(Shop shop) {
		return shopMapper.updateByPrimaryKeySelective(shop);
	}

	@Override
	public Long selectObjectListPageTotal(Shop shop) {
		return shopMapper.selectObjectListPageTotal(shop);
	}

	@Override
	public List<Shop> selectObjectListPage(Shop shop) {
		return shopMapper.selectObjectListPage(shop);
	}

	@Override
	public List<Shop> selectByObjectList(Shop shop){
		return shopMapper.selectByObjectList(shop);
	}

}
