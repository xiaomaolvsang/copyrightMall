package com.copyright.mall.serviceImpl;

import java.util.List;

import com.copyright.mall.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.ItemMapper;
import com.copyright.mall.service.IItemService;

import com.copyright.mall.bean.Item;


/**
 *
 * 商品表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class ItemService implements IItemService {

	private static Logger logger = LoggerFactory.getLogger(ItemService.class);

	@Resource
	private ItemMapper itemMapper;

	@Override
	public Item selectByPrimaryKey(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return itemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Item item) {
		return itemMapper.insertSelective(item);
	}

	@Override
	public int updateByPrimaryKeySelective(Item item) {
		return itemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public Long selectObjectListPageTotal(Item item) {
		return itemMapper.selectObjectListPageTotal(item);
	}

	@Override
	public List<Item> selectObjectListPage(Item item) {
		return itemMapper.selectObjectListPage(item);
	}

	@Override
	public List<Item> selectByObjectList(Item item){
		return itemMapper.selectByObjectList(item);
	}

}
