package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.MenuMapper;
import com.copyright.mall.service.IMenuService;

import com.copyright.mall.bean.Menu;


/**
 *
 * 菜单表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class MenuService implements IMenuService {

	private static Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Resource
	private MenuMapper menuMapper;

	@Override
	public Menu selectByPrimaryKey(Long id) {
		return menuMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Menu menu) {
		return menuMapper.insertSelective(menu);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu menu) {
		return menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public Long selectObjectListPageTotal(Menu menu) {
		return menuMapper.selectObjectListPageTotal(menu);
	}

	@Override
	public List<Menu> selectObjectListPage(Menu menu) {
		return menuMapper.selectObjectListPage(menu);
	}

	@Override
	public List<Menu> selectByObjectList(Menu menu){
		return menuMapper.selectByObjectList(menu);
	}

}
