package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.User;
import com.copyright.mall.bean.UserShopRelation;
import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.dao.ShopMapper;
import com.copyright.mall.manage.domain.dto.ArtistManageParam;
import com.copyright.mall.manage.domain.dto.PassArtistParam;
import com.copyright.mall.manage.domain.vo.ArtistManageResp;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.IUserShopRelationService;
import com.copyright.mall.util.MD5Util;
import com.copyright.mall.util.TimeUtil;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "艺术家审核")
@Slf4j
@RestController
@RequestMapping("/manage/v1/artistPass")
public class ManageArtistController extends BaseManageController {

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private IUserShopRelationService userShopRelationService;

    @Resource
    private IUserService userService;

    @PostMapping("/getArtist")
    @ApiOperation("获取艺术家")
    public Wrapper<PageInfo<ArtistManageResp>> getOpus(@ApiParam @Valid @RequestBody ArtistManageParam artistManageParam) {
        Shop shop = new Shop();
        shop.setMallId(1L);
        shop.setShopStatus(ShopStatusEnum.init.getCode());
        shop.setShopType(ShopTypeEnum.artist.getCode());
        shop.setShopName(artistManageParam.getArtistName());
        shop.setPhone(artistManageParam.getPhone());
        Page page = PageHelper.startPage(artistManageParam.getPageNum(), artistManageParam.getPageSize());
        List<Shop> shops = shopMapper.selectByObjectList(shop);

        List<ArtistManageResp> list = new ArrayList<>();
        shops.forEach(s -> {
            ArtistManageResp artistManageResp = new ArtistManageResp();
            artistManageResp.setArtCategory(s.getShopArtcategory());
            artistManageResp.setCreateTime(TimeUtil.formatDate(s.getGmtCreate()));
            artistManageResp.setLogo(s.getShopLogo());
            artistManageResp.setId(s.getId());
            artistManageResp.setName(s.getCompanyName());
            artistManageResp.setPetName(s.getShopName());
            artistManageResp.setPhone(s.getPhone());
            List<UserShopRelation> userShopRelations = userShopRelationService.selectByShopId(s.getId());
            if (!CollectionUtils.isEmpty(userShopRelations)) {
                UserShopRelation shopRelation = userShopRelations.get(0);
                User user = userService.selectByUserId(shopRelation.getUserId());
                artistManageResp.setUserPhone(user.getPhone());
                artistManageResp.setUserId(shopRelation.getUserId());
                list.add(artistManageResp);
            }
        });
        PageInfo pageInfo = PageInfo.of(list);
        pageInfo.setTotal(page.getTotal());
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/passArtist")
    @ApiOperation("审核艺术家")
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Boolean> passArtist(@ApiParam @Valid @RequestBody PassArtistParam passArtistParam) {
        Shop shop = new Shop();
        shop.setId(passArtistParam.getId());
        shop.setPhone(passArtistParam.getPhone());
        shop.setShopName(passArtistParam.getName());
        shop.setCompanyName(passArtistParam.getPetName());
        shop.setShopStatus(passArtistParam.getStatus());
        shop.setShopLogo(passArtistParam.getLogo());
        shop.setShopArtcategory(passArtistParam.getArtCategory());
        shopMapper.updateByPrimaryKeySelective(shop);

        User user = userService.selectByPhone(passArtistParam.getUserPhone());
        user.setPassword(MD5Util.digest(passArtistParam.getPasswd()));
        userService.saveOrUpdate(user);
        return WrapMapper.ok();
    }


}
