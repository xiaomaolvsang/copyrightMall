package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.AdminUser;



/**
 *
 * 菜单权限 + 数据权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IAdminUserService {

	public AdminUser selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(AdminUser adminUser);

	public int updateByPrimaryKeySelective(AdminUser adminUser);

	public Long selectObjectListPageTotal(AdminUser adminUser);

	public List<AdminUser> selectObjectListPage(AdminUser adminUser);

	public List<AdminUser> selectByObjectList(AdminUser adminUser);

}
