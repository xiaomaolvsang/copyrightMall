package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.MenuGroupMapper;
import com.copyright.mall.service.IMenuGroupService;

import com.copyright.mall.bean.MenuGroup;


/**
 *
 * 组权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class MenuGroupService implements IMenuGroupService {

	private static Logger logger = LoggerFactory.getLogger(MenuGroupService.class);

	@Resource
	private MenuGroupMapper menuGroupMapper;

	@Override
	public MenuGroup selectByPrimaryKey(Long id) {
		return menuGroupMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return menuGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MenuGroup menuGroup) {
		return menuGroupMapper.insertSelective(menuGroup);
	}

	@Override
	public int updateByPrimaryKeySelective(MenuGroup menuGroup) {
		return menuGroupMapper.updateByPrimaryKeySelective(menuGroup);
	}

	@Override
	public Long selectObjectListPageTotal(MenuGroup menuGroup) {
		return menuGroupMapper.selectObjectListPageTotal(menuGroup);
	}

	@Override
	public List<MenuGroup> selectObjectListPage(MenuGroup menuGroup) {
		return menuGroupMapper.selectObjectListPage(menuGroup);
	}

	@Override
	public List<MenuGroup> selectByObjectList(MenuGroup menuGroup){
		return menuGroupMapper.selectByObjectList(menuGroup);
	}

}
