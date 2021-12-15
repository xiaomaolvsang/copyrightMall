package com.copyright.mall.controller.artist;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.UserShopRelation;
import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.artist.ArtistLogo;
import com.copyright.mall.domain.requeest.artist.ArtistLogoParam;
import com.copyright.mall.domain.requeest.artist.ArtistParam;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.IUserShopRelationService;
import com.copyright.mall.service.impl.ShopService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.copyright.mall.util.wrapper.WrapMapper.wrap;

@RestController
@Api(tags = {"艺术家操作"})
@RequestMapping("/artist")
@Slf4j
public class ArtistController extends BaseController {

    @Resource
    private IShopService shopService;

    @Resource
    private IUserShopRelationService userShopRelationService;

    @ApiOperation(value = "申请艺术家")
    @PostMapping("/createArtist")
    @ControllerErro
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Boolean> createArtist(@RequestBody @ApiParam @Valid ArtistParam artistParam) {

        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(getUserId());
        Shop shop = new Shop();
        UserShopRelation userShopRelation = new UserShopRelation();
        if (userShopRelations.size() > 0) {
            Shop shop1 = shopService.selectByPrimaryKey(userShopRelations.get(0).getShopId());
            if (shop1.getShopType() == ShopTypeEnum.artist.getCode()
                    && shop1.getShopStatus() == ShopStatusEnum.erro.getCode()) {
                shop.setId(userShopRelations.get(0).getShopId());
                userShopRelation.setId(userShopRelations.get(0).getId());
            } else {
                return WrapMapper.error("无法申请");
            }
        }

        shop.setShopName(artistParam.getPetName());
        shop.setCompanyName(artistParam.getName());
        shop.setPhone(artistParam.getPhone());
        shop.setShopArtcategory(artistParam.getShopArtCategory());
        shop.setShopLogo(artistParam.getLogo());
        shop.setShopImg(artistParam.getOpusImg());
        shop.setShopType(ShopTypeEnum.artist.getCode());
        shop.setCertification(artistParam.getCertification());
        shop.setShopStatus(ShopStatusEnum.init.getCode());
        shop.setIdCard(artistParam.getIdCardImg());

        shop.setMallId(1L);
        if (shop.getId() == null) {
            shopService.insertSelective(shop);
        } else {
            shopService.updateByPrimaryKeySelective(shop);
        }

        userShopRelation.setUserId(getUserId());
        userShopRelation.setShopId(shop.getId());
        if (userShopRelation.getId() == null) {
            userShopRelationService.insertSelective(userShopRelation);
        }
        return WrapMapper.ok();
    }

    @ApiOperation(value = "修改艺术家头像及代表作")
    @PostMapping("/updateArtist")
    @ControllerErro
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Boolean> updateArtist(@RequestBody @ApiParam @Valid ArtistLogoParam artistLogoParam) {
        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(getUserId());
        if (userShopRelations.size() > 0) {
            Shop shop1 = shopService.selectByPrimaryKey(userShopRelations.get(0).getShopId());
            if (shop1.getShopType() == ShopTypeEnum.artist.getCode()
                    && shop1.getShopStatus() == ShopStatusEnum.success.getCode()) {
                shop1.setShopLogo(artistLogoParam.getLogo());
                shop1.setShopImg(artistLogoParam.getOpusImg());
                shopService.updateByPrimaryKeySelective(shop1);
            }
        }
        return WrapMapper.ok();
    }


    @ApiOperation(value = "获取艺术家头像及代表作")
    @PostMapping("/getArtist")
    @ControllerErro
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<ArtistLogo> getArtist() {
        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(getUserId());
        ArtistLogo artistLogo = new ArtistLogo();
        if (userShopRelations.size() > 0) {
            Shop shop1 = shopService.selectByPrimaryKey(userShopRelations.get(0).getShopId());
            artistLogo.setLogo(shop1.getShopLogo());
            artistLogo.setOpusImg(shop1.getShopImg());
            artistLogo.setArtistName(shop1.getShopName());
        }
        return WrapMapper.ok(artistLogo);
    }


    @ApiOperation(value = "判断艺术家")
    @PostMapping("/ifArtist")
    @ControllerErro
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Boolean> ifArtist() {
        Long userId = getUserId();
        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(userId);
        if (CollectionUtils.isEmpty(userShopRelations)) {
            return wrap(10010, "请申请艺术家");
        }
        Optional<UserShopRelation> userShopRelation = userShopRelations.stream().findFirst();
        if (userShopRelation.isPresent()) {
            UserShopRelation userShopRelation1 = userShopRelation.get();
            Shop shop = shopService.selectByPrimaryKey(userShopRelation1.getShopId());
            if (shop == null) {
                return wrap(10030, "数据错误");
            }
            if (shop.getShopType() == ShopTypeEnum.artist.getCode()
                    && shop.getShopStatus() == ShopStatusEnum.success.getCode()) {
                return wrap(10000, "就是艺术家");
            } else if (shop.getShopType() == ShopTypeEnum.artist.getCode()
                    && shop.getShopStatus() == ShopStatusEnum.init.getCode()) {
                return wrap(10040, "审核中");
            } else if (shop.getShopType() == ShopTypeEnum.artist.getCode()
                    && shop.getShopStatus() == ShopStatusEnum.erro.getCode()) {
                return wrap(10050, "被驳回");
            } else {
                return wrap(10020, "无法申请艺术家");
            }
        } else {
            return wrap(10010, "请申请艺术家");
        }
    }
}
