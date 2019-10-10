package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Menu;



/**
 * 
 * 菜单表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface MenuMapper {

	public Menu selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Menu menu);

	public int updateByPrimaryKeySelective(Menu menu);

	public Long selectObjectListPageTotal(Menu menu);

	public List<Menu> selectObjectListPage(Menu menu);

	public List<Menu> selectByObjectList(Menu menu);

}
