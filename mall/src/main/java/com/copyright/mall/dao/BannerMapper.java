package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.bean.BannerItemRelation;
import org.apache.ibatis.annotations.Param;


/**
 *
 *
 * @author lijian
 * @date 2019-11-23 19:24
 **/
public interface BannerMapper {

	public Banner selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Banner banner);

	public int updateByPrimaryKeySelective(Banner banner);

	public Long selectObjectListPageTotal(Banner banner);

	public List<Banner> selectObjectListPage(Banner banner);

	public List<Banner> selectByObjectList(Banner banner);

	List<Banner> selectBanner(Banner banner);

	public List<Banner> selectByBannerIds(@Param("bannerIds") List<Long> bannerIds);
}
