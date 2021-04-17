package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.bean.BannerAttr;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-23 19:24
 **/
public interface BannerAttrMapper {

	public BannerAttr selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(BannerAttr bannerAttr);

	public int updateByPrimaryKeySelective(BannerAttr bannerAttr);

	public Long selectObjectListPageTotal(BannerAttr bannerAttr);

	public List<BannerAttr> selectObjectListPage(BannerAttr bannerAttr);

	public List<BannerAttr> selectByObjectList(BannerAttr bannerAttr);

	public List<BannerAttr> selectByBannerIds(@Param("bannerIds") List<Long> bannerIds);

}
