package com.copyright.mall.service.impl;

import com.copyright.mall.bean.BannerItemRelation;
import com.copyright.mall.dao.BannerItemRelationMapper;
import com.copyright.mall.service.IBannerItemRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 *
 * @author lijian
 * @date 2019-11-24 14:53
 **/
@Service
public class BannerItemRelationService implements IBannerItemRelationService {

	private static Logger logger = LoggerFactory.getLogger(BannerItemRelationService.class);

	@Resource
	private BannerItemRelationMapper bannerItemRelationMapper;

	@Override
	public BannerItemRelation selectByPrimaryKey(Long id) {
		return bannerItemRelationMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return bannerItemRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(BannerItemRelation bannerItemRelation) {
		return bannerItemRelationMapper.insertSelective(bannerItemRelation);
	}

	@Override
	public int updateByPrimaryKeySelective(BannerItemRelation bannerItemRelation) {
		return bannerItemRelationMapper.updateByPrimaryKeySelective(bannerItemRelation);
	}

	@Override
	public Long selectObjectListPageTotal(BannerItemRelation bannerItemRelation) {
		return bannerItemRelationMapper.selectObjectListPageTotal(bannerItemRelation);
	}

	@Override
	public List<BannerItemRelation> selectObjectListPage(BannerItemRelation bannerItemRelation) {
		return bannerItemRelationMapper.selectObjectListPage(bannerItemRelation);
	}

	@Override
	public List<BannerItemRelation> selectByObjectList(BannerItemRelation bannerItemRelation){
		return bannerItemRelationMapper.selectByObjectList(bannerItemRelation);
	}

}
