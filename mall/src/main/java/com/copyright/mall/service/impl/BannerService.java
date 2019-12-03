package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.bean.BannerItemRelation;
import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.enumeration.MallType;
import com.copyright.mall.dao.BannerMapper;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.banner.ArtBannerParam;
import com.copyright.mall.domain.requeest.banner.BannerParam;
import com.copyright.mall.domain.vo.banner.ArtBannerVO;
import com.copyright.mall.domain.vo.banner.BannerVO;
import com.copyright.mall.service.IBannerService;
import com.copyright.mall.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 *
 * @author lijian
 * @date 2019-11-23 19:24
 **/
@Service
public class BannerService implements IBannerService {

	private static Logger logger = LoggerFactory.getLogger(BannerService.class);

	@Resource
	private BannerMapper bannerMapper;

	@Resource
	private BannerItemRelationService bannerItemRelationService;

  @Resource
  private IItemService itemService;

  @Resource
  private ShopService shopService;

	@Override
	public Banner selectByPrimaryKey(Long id) {
		return bannerMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return bannerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Banner banner) {
		return bannerMapper.insertSelective(banner);
	}

	@Override
	public int updateByPrimaryKeySelective(Banner banner) {
		return bannerMapper.updateByPrimaryKeySelective(banner);
	}

	@Override
	public Long selectObjectListPageTotal(Banner banner) {
		return bannerMapper.selectObjectListPageTotal(banner);
	}

	@Override
	public List<Banner> selectObjectListPage(Banner banner) {
		return bannerMapper.selectObjectListPage(banner);
	}

	@Override
	public List<Banner> selectByObjectList(Banner banner){
		return bannerMapper.selectByObjectList(banner);
	}

  @Override
  public BannerVO getBanner(BannerParam bannerParam) {
    Banner banner = new Banner();
    banner.setMallType(bannerParam.getType());
    banner.setMallId(bannerParam.getMallId());
    List<Banner> banners = bannerMapper.selectBanner(banner);
    BannerVO result = new BannerVO();
    List<BannerVO.BannerList> bannerLists = new ArrayList<>();
    List<BannerVO.Products> products = new ArrayList<>();

    banners.forEach(banner1 -> {
      BannerVO.BannerList bannerList = new BannerVO.BannerList();

      bannerList.setHeight(String.valueOf(banner1.getHeight()));
      bannerList.setWidth(String.valueOf(banner1.getWidth()));
      bannerList.setType(banner1.getType());
      List<BannerVO.Attributes> attributes = new ArrayList<>();

      banner1.getBannerAttrs().forEach(attr ->{
        BannerVO.Attributes attributes1 = new BannerVO.Attributes();
        BeanUtils.copyProperties(attr,attributes1);
        attributes.add(attributes1);
      });

      bannerList.setData(attributes);
      if("productResource".equals(banner1.getType())){

        BannerItemRelation bannerItemRelation = new BannerItemRelation();
        bannerItemRelation.setBannerId(banner1.getId());
        List<BannerItemRelation> list = bannerItemRelationService.selectByObjectList(bannerItemRelation);
        list.forEach(item ->{
          BannerVO.Products product = new BannerVO.Products();
          product.setProductId(item.getItemId());
          product.setLinkType("product");
          product.setProductImage(item.getDataImg());
          product.setProductName(item.getDataName());
          product.setTargetUrl("");
          products.add(product);
        });
        bannerList.setProducts(products);
      }
      if("artistList".equals(banner1.getType())){
        BannerItemRelation bannerItemRelation = new BannerItemRelation();
        bannerItemRelation.setBannerId(banner1.getId());
        List<BannerItemRelation> list = bannerItemRelationService.selectByObjectList(bannerItemRelation);
        list.forEach(shop1 -> {
          BannerVO.Products product = new BannerVO.Products();
          product.setProductId(shop1.getItemId());
          product.setLinkType("artist");
          product.setProductImage(shop1.getDataImg());
          product.setProductName(shop1.getDataName());
          product.setTargetUrl("");
          products.add(product);
        });

      }
      bannerLists.add(bannerList);
    });
    result.setData(bannerLists);
    return result;
  }

  @Override
  public ArtBannerVO getArtBanner(ArtBannerParam artBannerParam) {
    Banner banner = new Banner();
    banner.setMallType("artist");
    banner.setMallId(artBannerParam.getMallId());
    List<Banner> banners = bannerMapper.selectBanner(banner);
    ArtBannerVO artBannerVO = new ArtBannerVO();
    List<ArtBannerVO.BannerList> bannerLists = new ArrayList<>();
    List<ArtBannerVO.Artists> artist = new ArrayList<>();
    banners.forEach(banner1 -> {
      ArtBannerVO.BannerList bannerList = new ArtBannerVO.BannerList();
      bannerList.setHeight(String.valueOf(banner1.getHeight()));
      bannerList.setWidth(String.valueOf(banner1.getWidth()));
      bannerList.setType(banner1.getType());
      List<BannerVO.Attributes> attributes = new ArrayList<>();
      banner1.getBannerAttrs().forEach(attr -> {
        BannerVO.Attributes attributes1 = new BannerVO.Attributes();
        BeanUtils.copyProperties(attr, attributes1);
        attributes.add(attributes1);
      });
      bannerList.setData(attributes);
      if ("artistList".equals(banner1.getType())) {
        BannerItemRelation bannerItemRelation = new BannerItemRelation();
        bannerItemRelation.setBannerId(banner1.getId());
        List<BannerItemRelation> list = bannerItemRelationService.selectByObjectList(bannerItemRelation);
        list.forEach(item -> {
          ArtBannerVO.Artists art = new ArtBannerVO.Artists();
          art.setArtistId(item.getItemId());
          art.setArtistName(item.getDataName());
          art.setPosterPic(item.getDataImg());
          artist.add(art);
        });
      bannerList.setArtist(artist);
      }
      bannerLists.add(bannerList);

    });

    artBannerVO.setData(bannerLists);
    return artBannerVO;
  }


}
