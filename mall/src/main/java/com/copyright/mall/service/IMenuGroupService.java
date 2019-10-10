package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.MenuGroup;



/**
 *
 * 组权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IMenuGroupService {

	public MenuGroup selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(MenuGroup menuGroup);

	public int updateByPrimaryKeySelective(MenuGroup menuGroup);

	public Long selectObjectListPageTotal(MenuGroup menuGroup);

	public List<MenuGroup> selectObjectListPage(MenuGroup menuGroup);

	public List<MenuGroup> selectByObjectList(MenuGroup menuGroup);

}
