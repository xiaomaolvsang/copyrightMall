package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Item;
import com.copyright.mall.config.GuavaManage;
import com.copyright.mall.dao.ItemMapper;
import com.copyright.mall.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 商品表
 *
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class ItemService implements IItemService {

    private static Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private GuavaManage guavaManage;

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
    public List<Item> selectByObjectList(Item item) {
        return itemMapper.selectByObjectList(item);
    }

    @Override
    public List<Item> selectAll() {
        Optional<Object> infoOptional = guavaManage.getCache(getKey(),
                () -> Optional.ofNullable(itemMapper.selectAllItem()));
        List<Item> items = new ArrayList<>();
        if (infoOptional.isPresent()) {
            items = (List<Item>) infoOptional.get();
        }
        return items;
    }

    @Override
    public List<Item> selectItemsByParam(List<Long> shopIds, String itemTitle,
                                         List<Long> itemClassIds, String barcode,
                                         Long id, Integer itemStatus, String shopType,
                                         Integer startOfPage, Integer pageSize) {
        return itemMapper.selectItemsByParam(shopIds, itemTitle, itemClassIds,
                barcode, id, itemStatus, shopType, startOfPage, pageSize);
    }

    @Override
    public int selectItemsCountByParam(List<Long> shopIds, String itemTitle, List<Long> itemClassIds, String barcode, Long id, Integer itemStatus, String shopType) {
        return itemMapper.selectItemsCountByParam(shopIds, itemTitle, itemClassIds,
                barcode, id, itemStatus, shopType);
    }

    private String getKey() {
        return "item";
    }



}
