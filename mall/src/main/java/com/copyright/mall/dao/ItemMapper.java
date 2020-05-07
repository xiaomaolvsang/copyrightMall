package com.copyright.mall.dao;

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
public interface ItemMapper {

	public Item selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Item item);

	public int updateByPrimaryKeySelective(Item item);

	public Long selectObjectListPageTotal(Item item);

	public List<Item> selectObjectListPage(Item item);

	public List<Item> selectByObjectList(Item item);

	List<Item> selectAllItem();

	List<Item> selectItemsByParam(@Param("shopIds") List<Long> shopIds,
								  @Param("itemTitle") String itemTitle,
								  @Param("itemClassIds") List<Long> itemClassIds,
								  @Param("barcode") String barcode,
								  @Param("id") Long id,
								  @Param("itemStatus") Integer itemStatus,
								  @Param("shopType") String shopType,
								  @Param("startOfPage")Integer startOfPage,
								  @Param("pageSize") Integer pageSize);

	int selectItemsCountByParam(@Param("shopIds") List<Long> shopIds,
								@Param("itemTitle") String itemTitle,
								@Param("itemClassIds") List<Long> itemClassIds,
								@Param("barcode") String barcode,
								@Param("id") Long id,
								@Param("itemStatus") Integer itemStatus,
								@Param("shopType") String shopType);
}
