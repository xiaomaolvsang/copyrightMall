package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.AdminUserMapper;
import com.copyright.mall.service.IAdminUserService;

import com.copyright.mall.bean.AdminUser;


/**
 *
 * 菜单权限 + 数据权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class AdminUserService implements IAdminUserService {

	private static Logger logger = LoggerFactory.getLogger(AdminUserService.class);

	@Resource
	private AdminUserMapper adminUserMapper;

	@Override
	public AdminUser selectByPrimaryKey(Long id) {
		return adminUserMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return adminUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(AdminUser adminUser) {
		return adminUserMapper.insertSelective(adminUser);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminUser adminUser) {
		return adminUserMapper.updateByPrimaryKeySelective(adminUser);
	}

	@Override
	public Long selectObjectListPageTotal(AdminUser adminUser) {
		return adminUserMapper.selectObjectListPageTotal(adminUser);
	}

	@Override
	public List<AdminUser> selectObjectListPage(AdminUser adminUser) {
		return adminUserMapper.selectObjectListPage(adminUser);
	}

	@Override
	public List<AdminUser> selectByObjectList(AdminUser adminUser){
		return adminUserMapper.selectByObjectList(adminUser);
	}

}
