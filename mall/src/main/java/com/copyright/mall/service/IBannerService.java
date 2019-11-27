package com.copyright.mall.service;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.domain.requeest.banner.ArtBannerParam;
import com.copyright.mall.domain.requeest.banner.BannerParam;
import com.copyright.mall.domain.vo.banner.ArtBannerVO;
import com.copyright.mall.domain.vo.banner.BannerVO;

import java.util.List;



/**
 *
 *
 * @author lijian
 * @date 2019-11-23 19:24
 **/
public interface IBannerService {

	public Banner selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Banner banner);

	public int updateByPrimaryKeySelective(Banner banner);

	public Long selectObjectListPageTotal(Banner banner);

	public List<Banner> selectObjectListPage(Banner banner);

	public List<Banner> selectByObjectList(Banner banner);

  public BannerVO getBanner(BannerParam bannerParam);

  public ArtBannerVO getArtBanner(ArtBannerParam artBannerParam);

}
