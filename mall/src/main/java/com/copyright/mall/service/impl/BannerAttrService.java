package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.bean.BannerAttr;
import com.copyright.mall.dao.BannerAttrMapper;
import com.copyright.mall.service.IBannerAttrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 *
 * @author lijian
 * @date 2019-11-23 19:24
 **/
@Service
public class BannerAttrService implements IBannerAttrService {

	private static Logger logger = LoggerFactory.getLogger(BannerAttrService.class);

	@Resource
	private BannerAttrMapper bannerAttrMapper;

	@Override
	public BannerAttr selectByPrimaryKey(Long id) {
		return bannerAttrMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return bannerAttrMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(BannerAttr bannerAttr) {
		return bannerAttrMapper.insertSelective(bannerAttr);
	}

	@Override
	public int updateByPrimaryKeySelective(BannerAttr bannerAttr) {
		return bannerAttrMapper.updateByPrimaryKeySelective(bannerAttr);
	}

	@Override
	public Long selectObjectListPageTotal(BannerAttr bannerAttr) {
		return bannerAttrMapper.selectObjectListPageTotal(bannerAttr);
	}

	@Override
	public List<BannerAttr> selectObjectListPage(BannerAttr bannerAttr) {
		return bannerAttrMapper.selectObjectListPage(bannerAttr);
	}

	@Override
	public List<BannerAttr> selectByObjectList(BannerAttr bannerAttr){
		return bannerAttrMapper.selectByObjectList(bannerAttr);
	}

	@Override
	public List<BannerAttr> selectByBannerIds(List<Long> bannerIds) {
		return bannerAttrMapper.selectByBannerIds(bannerIds);
	}

}
