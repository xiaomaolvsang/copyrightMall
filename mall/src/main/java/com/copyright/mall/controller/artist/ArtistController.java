package com.copyright.mall.controller.artist;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.UserShopRelation;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.requeest.artist.ArtistParam;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.IUserShopRelationService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
    public Wrapper<Boolean> createArtist(@RequestBody @ApiParam @Valid ArtistParam artistParam){

        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(getUserId());

        if(userShopRelations.size() > 0){
            return WrapMapper.error("审核中");
        }

        Shop shop = new Shop();
        shop.setShopName(artistParam.getPetName());
        shop.setCompanyName(artistParam.getName());
        shop.setPhone(artistParam.getPhone());
        shop.setShopArtcategory(artistParam.getShopArtCategory());
        shop.setShopLogo(artistParam.getLogo());
        shop.setShopImg(artistParam.getOpusImg());
        shop.setCertification(artistParam.getCertification());
        shopService.insertSelective(shop);

        UserShopRelation userShopRelation = new UserShopRelation();
        userShopRelation.setUserId(getUserId());
        userShopRelation.setShopId(shop.getId());
        userShopRelationService.insertSelective(userShopRelation);

        return WrapMapper.ok();
    }
}
