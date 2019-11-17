package com.copyright.mall.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import com.copyright.mall.config.GuavaManage;
import com.google.common.cache.Cache;
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

	@Resource
	private GuavaManage guavaManage;

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
    Optional<List<Shop>> infoOptional = guavaManage.getCache(getKey(shop.getId()),() ->
      shopMapper.selectByObjectList(shop));
    List<Shop> result = infoOptional.orElse(null);
    return result;
	}

	private String getKey(Long id){
	  return "shop"+id;
  }

}
