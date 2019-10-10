package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.CartMapper;
import com.copyright.mall.service.ICartService;

import com.copyright.mall.bean.Cart;


/**
 *
 * 购物车
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class CartService implements ICartService {

	private static Logger logger = LoggerFactory.getLogger(CartService.class);

	@Resource
	private CartMapper cartMapper;

	@Override
	public Cart selectByPrimaryKey(Long id) {
		return cartMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return cartMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Cart cart) {
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

}
