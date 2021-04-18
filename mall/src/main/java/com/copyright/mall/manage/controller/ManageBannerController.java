package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.Banner;
import com.copyright.mall.bean.BannerAttr;
import com.copyright.mall.bean.BannerItemRelation;
import com.copyright.mall.manage.domain.dto.BannerPageParam;
import com.copyright.mall.manage.domain.dto.BannerParam;
import com.copyright.mall.manage.domain.dto.QueryGoodsParam;
import com.copyright.mall.manage.domain.vo.GetBannerResp;
import com.copyright.mall.manage.domain.vo.GetGoodsResp;
import com.copyright.mall.service.IBannerAttrService;
import com.copyright.mall.service.IBannerItemRelationService;
import com.copyright.mall.service.IBannerService;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "后端首页编辑")
@Slf4j
@RestController
@RequestMapping("/manage/v1/banner")
public class ManageBannerController {

    @Resource
    private IBannerAttrService bannerAttrService;

    @Resource
    private IBannerService bannerService;

    @Resource
    private IBannerItemRelationService bannerItemRelationService;

    @PostMapping("/getBanners")
    @ApiOperation("获取banner信息")
    public Wrapper<PageInfo<GetBannerResp>> getBanners(@ApiParam @RequestBody BannerPageParam bannerPageParam) {
        log.info("getBanner {}", bannerPageParam);
        List<Banner> banners = bannerService.selectByObjectList(new Banner());
        Map<Long,String> bannerTypeMap = banners.stream().collect(Collectors.toMap(Banner::getId,Banner::getType));
        Page page = PageHelper.startPage(bannerPageParam.getPageNum(), bannerPageParam.getPageSize());
        List<BannerAttr> bannerAttrs =  bannerAttrService.selectByObjectList(new BannerAttr());
        List<GetBannerResp> getBannerResps = new ArrayList<>();

        for(BannerAttr bannerAttr : bannerAttrs){
            GetBannerResp getBannerResp = new GetBannerResp();
            getBannerResp.setId(bannerAttr.getId());
            getBannerResp.setBannerType(bannerTypeMap.get(bannerAttr.getBannerId()));
            getBannerResp.setImage(bannerAttr.getImage());
            getBannerResp.setCategoryName(bannerAttr.getCategoryName());
            getBannerResp.setLinkType(bannerAttr.getLinkType());
            getBannerResp.setTargetUrl(bannerAttr.getTargetUrl());
            getBannerResps.add(getBannerResp);
        }

        PageInfo res = PageInfo.of(getBannerResps);
        res.setTotal(page.getTotal());
        return WrapMapper.ok(res);
    }

    @PostMapping("/updateBanners")
    @ApiOperation("修改banner信息")
    public Wrapper<Boolean> updateBanners(@ApiParam  @RequestBody BannerParam bannerParam) {
        log.info("getBanner {}", bannerParam);
        BannerAttr bannerAttr = new BannerAttr();
        bannerAttr.setId(bannerParam.getId());
        bannerAttr.setImage(bannerParam.getImage());
        bannerAttr.setLinkType(bannerParam.getLinkType());
        bannerAttr.setTargetUrl(bannerParam.getTargetUrl());
        bannerAttr.setCategoryName(bannerParam.getCategoryName());
        BannerAttr bannerAttr1 = bannerAttrService.selectByPrimaryKey(bannerParam.getId());
        Long bannerId = bannerAttr1.getBannerId();
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setType(bannerParam.getBannerType());
        bannerService.updateByPrimaryKeySelective(banner);
        bannerAttrService.updateByPrimaryKeySelective(bannerAttr);
        return  WrapMapper.ok();
    }


}
