package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.Certificate;
import com.copyright.mall.bean.Copyright;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.domain.dto.copyright.ArtistResp;
import com.copyright.mall.domain.dto.copyright.CertificateDetail;
import com.copyright.mall.domain.dto.copyright.CopyrightQueryParam;
import com.copyright.mall.domain.dto.copyright.TimeLineDTO;
import com.copyright.mall.enums.CopyRightStatusEnum;
import com.copyright.mall.manage.domain.dto.CertificateParam;
import com.copyright.mall.service.ICertificateService;
import com.copyright.mall.service.ICopyrightService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.impl.ShopService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "后端版权查询")
@Slf4j
@RestController
@RequestMapping("/manage/v1/certificate")
public class CertificateController {

    @Resource
    private ICertificateService certificateService;

    @Resource
    private ICopyrightService copyrightService;

    @Resource
    private IShopService shopService;

    @PostMapping("/examine")
    @ApiOperation("审核")
    public Wrapper<Boolean> examine(@RequestBody @Valid CertificateParam certificateParam){
        if("certificate".equals(certificateParam.getType())){
            Copyright copyright = new Copyright();
            copyright.setId(Long.valueOf(certificateParam.getId()));
            if(0==certificateParam.getStatus()) {
                copyright.setAuditStatus(CopyRightStatusEnum.AUTHORIZED.getCode());
            }
            if(1 == certificateParam.getStatus()){
                copyright.setAuditStatus(CopyRightStatusEnum.REJECTED.getCode());
            }
            copyrightService.updateByPrimaryKeySelective(copyright);
            Certificate certificate = new Certificate();
            certificate.setId(Integer.valueOf(certificateParam.getId()));
            List<CertificateDetail> checkList  = certificateService.selectListDetail(certificate);
            if(!CollectionUtils.isEmpty(checkList)){
                CertificateDetail check = checkList.get(0);
                TimeLineDTO timeLineDTO = TimeLineDTO.fromBaseStr(check.getTimeLine());
                TimeLineDTO.TimelineItem item = new TimeLineDTO.TimelineItem();
                item.setTime(new Date());
                if(0==certificateParam.getStatus()) {
                    if (check.getType() == 0) {
                        certificate.setCerificateStatus(CopyRightStatusEnum.CONFIRMED_RIGHT.getCode());
                        item.setEvent("版权链证书颁发");
                    } else {
                        certificate.setCerificateStatus(CopyRightStatusEnum.AUTHORIZED.getCode());
                        item.setEvent("版权链证书颁发");
                    }
                    certificate.setAuthorizationDate(new Date());
                }else{
                    certificate.setCerificateStatus(CopyRightStatusEnum.REJECTED.getCode());
                    item.setEvent("授权驳回");
                }
                timeLineDTO.appendItem(item);
                certificate.setTimeLine(timeLineDTO.toBaseString());
                certificateService.updateByPrimaryKeySelective(certificate);
            }
        }
        if("artist".equals(certificateParam.getType())){
            Shop shop = new Shop();
            shop.setId(Long.valueOf(certificateParam.getId()));
            if(0==certificateParam.getStatus()){
                shop.setShopStatus(ShopStatusEnum.success.getCode());
            }
            if(1==certificateParam.getStatus()){
                shop.setShopStatus(ShopStatusEnum.erro.getCode());
            }
            shopService.updateByPrimaryKeySelective(shop);
        }
        return WrapMapper.ok(true);
    }

    @GetMapping("/listCertificate")
    @ApiOperation("获取证书列表")
    public Wrapper<PageInfo<CertificateDetail>> listCertificate(@Valid CopyrightQueryParam queryParam){
        Certificate certificate = new Certificate();
        PageHelper.startPage(queryParam.getPageNum(),queryParam.getPageSize());
        certificate.setCerificateStatus(queryParam.getStatus());
        PageInfo<CertificateDetail> copyrights = PageInfo.of(certificateService.selectListDetail(certificate));
        return WrapMapper.ok(copyrights);
    }

    @GetMapping("/listArtist")
    @ApiOperation("获取艺术家审核列表")
    public Wrapper<PageInfo<ArtistResp>> listArtist(@Valid CopyrightQueryParam queryParam){
        Shop shop = new Shop();
        shop.setMallId(1L);
        List<Shop> shops = shopService.selectByObjectList(shop);
        List<Shop> shopsA = shops.stream().filter(shop1 -> {
            if(queryParam.getStatus() == 10){
                return shop1.getShopType() == ShopTypeEnum.artist.getCode() && shop1.getShopStatus() == ShopStatusEnum.init.getCode();
            }
            if(queryParam.getStatus() == 20){
                return shop1.getShopType() == ShopTypeEnum.artist.getCode() && shop1.getShopStatus() == ShopStatusEnum.erro.getCode();
            }
            if(queryParam.getStatus() == 30){
                return shop1.getShopType() == ShopTypeEnum.artist.getCode() && shop1.getShopStatus() == ShopStatusEnum.success.getCode();
            }
            return false;
        }).collect(Collectors.toList());
        List<Shop> shops1 = shopsA.stream().skip(queryParam.getPageSize() * (queryParam.getPageNum() - 1)).limit(queryParam.getPageSize()).collect(Collectors.toList());
        List<ArtistResp> list = new ArrayList<>();
        shops1.forEach(shop1 -> {
            ArtistResp artistResp = new ArtistResp();
            artistResp.setCertification(shop1.getCertification());
            artistResp.setId(shop1.getId());
            artistResp.setLogo(shop1.getShopLogo());
            artistResp.setName(shop1.getCompanyName());
            artistResp.setPetName(shop1.getShopName());
            artistResp.setOpusImg(shop1.getShopImg());
            artistResp.setPhone(shop1.getPhone());
            artistResp.setShopArtCategory(shop1.getShopArtcategory());
            list.add(artistResp);
        });
        PageInfo<ArtistResp> pageInfo = PageInfo.of(list);
        pageInfo.setTotal(shopsA.size());
        return WrapMapper.ok(pageInfo);
    }



    public static void main(String[] args) {
        System.out.println(TimeLineDTO.fromBaseStr("eyJpdGVtcyI6W3siZXZlbnQiOiLmj5DkuqTnlLPor7ciLCJ0aW1lIjoxNTkxNzc1NTM4ODI5fSx7ImV2ZW50Ijoi54mI5p2D6ZO+6K+B5Lmm6aKB5Y+RIiwidGltZSI6MTU5MTc3NTg1NjQ0NH0seyJldmVudCI6IueJiOadg+mTvuaOiOadgyIsInRpbWUiOjE1OTE3Nzc4MzI4Mzh9XX0="));
    }
}
