package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.BannerItemRelation;



/**
 * 
 * 
 * @author lijian
 * @date 2019-11-24 14:53
 **/
public interface BannerItemRelationMapper {

	public BannerItemRelation selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(BannerItemRelation bannerItemRelation);

	public int updateByPrimaryKeySelective(BannerItemRelation bannerItemRelation);

	public Long selectObjectListPageTotal(BannerItemRelation bannerItemRelation);

	public List<BannerItemRelation> selectObjectListPage(BannerItemRelation bannerItemRelation);

	public List<BannerItemRelation> selectByObjectList(BannerItemRelation bannerItemRelation);

}
