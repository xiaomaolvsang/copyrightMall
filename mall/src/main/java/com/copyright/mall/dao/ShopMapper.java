package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Shop;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * 商铺表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ShopMapper {

	public Shop selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Shop shop);

	public int updateByPrimaryKeySelective(Shop shop);

	public Long selectObjectListPageTotal(Shop shop);

	public List<Shop> selectObjectListPage(Shop shop);

	public List<Shop> selectByObjectList(Shop shop);

	public List<Shop> selectPageByUserId(@Param("userId")Long userId,
										 @Param("startOfPage") Integer startOfPage,
										 @Param("pageSize") Integer pageSize);

	public List<Shop> selectByShopIds(@Param("shopIds") List<Long> shopIds);
}
