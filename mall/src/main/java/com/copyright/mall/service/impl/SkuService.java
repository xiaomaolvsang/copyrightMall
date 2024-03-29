package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Sku;
import com.copyright.mall.config.GuavaManage;
import com.copyright.mall.dao.SkuMapper;
import com.copyright.mall.service.ISkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


/**
 *
 * sku
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class SkuService implements ISkuService {

	private static Logger logger = LoggerFactory.getLogger(SkuService.class);

	@Resource
	private SkuMapper skuMapper;

  @Resource
  private GuavaManage guavaManage;

	@Override
	public Sku selectByPrimaryKey(Long id) {
    Optional<Object> infoOptional = guavaManage.getCache(getKey(id),
      () -> Optional.ofNullable(skuMapper.selectByPrimaryKey(id)));
    Sku sku =new Sku();
    if (infoOptional.isPresent()) {
      sku = (Sku) infoOptional.get();
    }
    return sku;
	}

	@Override
	public Sku selectByPrimaryKeyFromDBWithIncrSoldInventory(Long id , int inventory) {
		return skuMapper.selectByPrimaryKeyFromDBWithIncrSoldInventory(id , inventory);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {

		return skuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Sku sku) {
		return skuMapper.insertSelective(sku);
	}

	@Override
	public int updateByPrimaryKeySelective(Sku sku) {
		return skuMapper.updateByPrimaryKeySelective(sku);
	}

	@Override
	public int incrSoldInventoryByPrimaryKey(long id, int soldInventory, int append) {
		return skuMapper.incrSoldInventoryByPrimaryKey(id, soldInventory, append);
	}


	@Override
	public Long selectObjectListPageTotal(Sku sku) {
		return skuMapper.selectObjectListPageTotal(sku);
	}

	@Override
	public List<Sku> selectObjectListPage(Sku sku) {
		return skuMapper.selectObjectListPage(sku);
	}

	@Override
	public List<Sku> selectByObjectList(Sku sku){
		return skuMapper.selectByObjectList(sku);
	}

	public String getKey(Long id){
	  return "sku"+id;
  }

}
