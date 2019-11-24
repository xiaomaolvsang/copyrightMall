package com.copyright.mall.service;

import com.copyright.mall.bean.BannerItemRelation;

import java.util.List;



/**
 *
 *
 * @author lijian
 * @date 2019-11-24 14:53
 **/
public interface IBannerItemRelationService {

	public BannerItemRelation selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(BannerItemRelation bannerItemRelation);

	public int updateByPrimaryKeySelective(BannerItemRelation bannerItemRelation);

	public Long selectObjectListPageTotal(BannerItemRelation bannerItemRelation);

	public List<BannerItemRelation> selectObjectListPage(BannerItemRelation bannerItemRelation);

	public List<BannerItemRelation> selectByObjectList(BannerItemRelation bannerItemRelation);

}
