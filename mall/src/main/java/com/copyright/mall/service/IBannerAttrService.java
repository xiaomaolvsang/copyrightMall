package com.copyright.mall.service;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.bean.BannerAttr;
import org.apache.ibatis.annotations.Param;

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

	public List<BannerAttr> selectByBannerIds(List<Long> bannerIds);

}
