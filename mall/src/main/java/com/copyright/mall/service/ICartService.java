package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Cart;



/**
 *
 * 购物车
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface ICartService {

	public Cart selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Cart cart);

	public int updateByPrimaryKeySelective(Cart cart);

	public Long selectObjectListPageTotal(Cart cart);

	public List<Cart> selectObjectListPage(Cart cart);

	public List<Cart> selectByObjectList(Cart cart);

}
