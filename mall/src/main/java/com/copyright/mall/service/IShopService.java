package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Shop;
import com.copyright.mall.manage.domain.dto.ModifyShopParam;
import com.copyright.mall.manage.domain.dto.QueryGoodsParam;
import com.copyright.mall.manage.domain.dto.QueryShopParam;
import com.copyright.mall.manage.domain.vo.ShopListRes;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;


/**
 *
 * 商铺表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IShopService {

	public Shop selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Shop shop);

	public int updateByPrimaryKeySelective(Shop shop);

	public Long selectObjectListPageTotal(Shop shop);

	public List<Shop> selectObjectListPage(Shop shop);

	public List<Shop> selectByObjectList(Shop shop);

	public Wrapper<Boolean> insertOrUpdateByParam(ModifyShopParam modifyShopParam);

	public Wrapper<PageInfo<ShopListRes>> getShopListByUserId(QueryShopParam queryShopParam);
}
