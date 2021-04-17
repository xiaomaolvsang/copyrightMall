package com.copyright.mall.service;

import com.copyright.mall.bean.Sku;

import java.util.List;


/**
 * sku
 *
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ISkuService {

    public Sku selectByPrimaryKey(Long id);

    public Sku selectByPrimaryKeyFromDBWithIncrSoldInventory(Long id, int inventory);

    public int deleteByPrimaryKey(Long id);

    public int insertSelective(Sku sku);

    public int updateByPrimaryKeySelective(Sku sku);

    public int incrSoldInventoryByPrimaryKey(long id, int inventory, int append);

    public Long selectObjectListPageTotal(Sku sku);

    public List<Sku> selectObjectListPage(Sku sku);

    public List<Sku> selectByObjectList(Sku sku);

}
