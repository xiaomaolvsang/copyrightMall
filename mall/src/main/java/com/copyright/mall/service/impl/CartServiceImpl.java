package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Cart;
import com.copyright.mall.dao.CartMapper;
import com.copyright.mall.domain.dto.cart.CartDTO;
import com.copyright.mall.domain.dto.goods.SkuDTO;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.service.ICartService;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.BeanMapperUtils;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * 购物车
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Slf4j
@Service
public class CartServiceImpl implements ICartService {


	@Resource
	private CartMapper cartMapper;

	@Resource
	private IProductService productService;

	@Override
	public Cart selectByPrimaryKey(Long id) {
		return cartMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return cartMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insertSelective(CartDTO cart) {
		return cartMapper.insertSelective(cart);
	}

	@Override
	public int updateByPrimaryKeySelective(Cart cart) {
		return cartMapper.updateByPrimaryKeySelective(cart);
	}

	@Override
	public Long selectObjectListPageTotal(Cart cart) {
		return cartMapper.selectObjectListPageTotal(cart);
	}

	@Override
	public List<Cart> selectObjectListPage(Cart cart) {
		return cartMapper.selectObjectListPage(cart);
	}

	@Override
	public List<Cart> selectByObjectList(Cart cart){
		return cartMapper.selectByObjectList(cart);
	}

	@Override
	public boolean modifyCart(CartDTO cartDTO) {
		SkuDTO skuDTO =  productService.querySingleItemBySku(cartDTO.getSkuId());
		if(skuDTO==null){
			log.warn("获取SKU为空=>[{}]",cartDTO.getSkuId());
			throw new BusinessException("获取SKU为空");
		}
		Cart checkExistsParam = new Cart();
		checkExistsParam.setMallId(cartDTO.getMallId());
		checkExistsParam.setShopId(cartDTO.getShopId());
		checkExistsParam.setItemId(skuDTO.getItemId());
		checkExistsParam.setSkuId(skuDTO.getId());
		checkExistsParam.setUserId(cartDTO.getUserId());
		checkExistsParam.setCartStatus(1);
		List<Cart> exists =  cartMapper.selectByObjectList(checkExistsParam);
		if(CollectionUtils.isEmpty(exists)){
			CartDTO createParam = BeanMapperUtils.map(checkExistsParam,CartDTO.class);
			Preconditions.checkArgument(cartDTO.getModifyCount()>0,"加车数量必须大于0");
			cartDTO.setCount(cartDTO.getModifyCount());
			cartMapper.insertSelective(createParam);
		}else{
			CartDTO updateParam = BeanMapperUtils.map(checkExistsParam,CartDTO.class);
			updateParam.setCount(updateParam.getCount()+cartDTO.getModifyCount());
			cartMapper.updateByPrimaryKeySelective(updateParam);
		}
		return true;
	}

}
