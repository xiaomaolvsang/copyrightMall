package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Cart;
import com.copyright.mall.domain.dto.cart.CartDTO;


/**
 *
 * 购物车
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ICartService {

	public Cart selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(CartDTO cart);

	public int updateByPrimaryKeySelective(Cart cart);

	public Long selectObjectListPageTotal(Cart cart);

	public List<Cart> selectObjectListPage(Cart cart);

	public List<Cart> selectByObjectList(Cart cart);

	/**
	 * modify cart
	 * @param cartDTO modify param
	 * @return is success
	 */
	boolean modifyCart(CartDTO cartDTO);

	List<CartDTO> selectCartListOrderByShop(CartDTO shopId);

}
