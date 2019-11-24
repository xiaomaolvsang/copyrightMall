package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.bean.BannerItemRelation;
import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.enumeration.MallType;
import com.copyright.mall.dao.BannerMapper;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.banner.BannerParam;
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
    if(!MallType.ifMallType(bannerParam.getType())){
      throw new BusinessException("商城与版权字段错误");
    }
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
        List<Long> itemIds = list.stream().map(BannerItemRelation::getItemId).collect(Collectors.toList());
        List<Item> items = itemService.selectAll().stream().filter(item -> itemIds.contains(item.getId())).collect(Collectors.toList());
        items.forEach(item ->{
          BannerVO.Products product = new BannerVO.Products();
          product.setProductId(item.getId());
          product.setLinkType("");
          product.setProductImage(item.getTitleImg());
          product.setProductName(item.getItemTitle());
          product.setTargetUrl(item.getContentImg());
          products.add(product);
        });
        bannerList.setProducts(products);
      }
      bannerLists.add(bannerList);
    });
    result.setData(bannerLists);
    return result;
  }


}
