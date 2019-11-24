package com.copyright.mall.service;

import com.copyright.mall.bean.BannerAttr;

import java.util.List;



/**
 *
 *
 * @author lijian
 * @date 2019-11-23 19:24
 **/
public interface IBannerAttrService {

	public BannerAttr selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(BannerAttr bannerAttr);

	public int updateByPrimaryKeySelective(BannerAttr bannerAttr);

	public Long selectObjectListPageTotal(BannerAttr bannerAttr);

	public List<BannerAttr> selectObjectListPage(BannerAttr bannerAttr);

	public List<BannerAttr> selectByObjectList(BannerAttr bannerAttr);

}
