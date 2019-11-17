package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Item;



/**
 *
 * 商品表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ItemMapper {

	public Item selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Item item);

	public int updateByPrimaryKeySelective(Item item);

	public Long selectObjectListPageTotal(Item item);

	public List<Item> selectObjectListPage(Item item);

	public List<Item> selectByObjectList(Item item);

	List<Item> selectAllItem();
}
