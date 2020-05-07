package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Item;
import org.apache.ibatis.annotations.Param;


/**
 *
 * 商品表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IItemService {

	public Item selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Item item);

	public int updateByPrimaryKeySelective(Item item);

	public Long selectObjectListPageTotal(Item item);

	public List<Item> selectObjectListPage(Item item);

	public List<Item> selectByObjectList(Item item);

	List<Item> selectAll();

	List<Item> selectItemsByParam(List<Long> shopIds,
								  String itemTitle,
								  List<Long> itemClassIds,
								  String barcode,
								  Long id,
								  Integer itemStatus,
								  String shopType,
								  Integer startOfPage,
								  Integer pageSize);
	int selectItemsCountByParam(List<Long> shopIds,
								String itemTitle,
								List<Long> itemClassIds,
								String barcode,
								Long id,
								Integer itemStatus,
								String shopType);
}
