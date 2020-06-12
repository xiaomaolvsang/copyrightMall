package com.copyright.mall.service.impl;

import com.copyright.mall.bean.ArtistOpus;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.UserShopRelation;
import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.dao.ArtistOpusMapper;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.opus.CreateOpusReq;
import com.copyright.mall.domain.requeest.opus.DeleteOpusParam;
import com.copyright.mall.domain.requeest.opus.OpusParam;
import com.copyright.mall.domain.requeest.opus.OpusReq;
import com.copyright.mall.domain.vo.opus.OpusResp;
import com.copyright.mall.domain.vo.opus.OpusVO;
import com.copyright.mall.service.IOpusService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * OpusService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 5:23 下午
 */
@Service
public class OpusService implements IOpusService {

    @Resource
    private ArtistOpusMapper artistOpusMapper;

    @Resource
    private IShopService shopService;

    @Resource
    private UserShopRelationService userShopRelationService;

    @Override
    public OpusVO getOpus(OpusParam opusParam) {
        OpusVO opusVO = new OpusVO();
        OpusVO.DataBean dataBean = new OpusVO.DataBean();

        ArtistOpus artistOpus = artistOpusMapper.selectByPrimaryKey(opusParam.getOpusId());
        if (artistOpus == null) {
            throw new BusinessException("未找到任何数据");
        }
        Shop shop = shopService.selectByPrimaryKey(artistOpus.getItemId());

        List<OpusVO.DataBean.ProductImageBean> list = new ArrayList<>();

        dataBean.setArtistAvatar(shop.getShopLogo());
        dataBean.setArtistName(shop.getShopName());
        dataBean.setProductDesc(shop.getCertification());
        dataBean.setProductTitle(shop.getShopName());
        dataBean.setPublishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(artistOpus.getGmtModified()));


        String imgs = artistOpus.getImgs();
        if (StringUtils.isNotBlank(imgs)) {

            String[] imgArray = imgs.split(",");
            for (String img : imgArray) {
                OpusVO.DataBean.ProductImageBean productImageBean = new OpusVO.DataBean.ProductImageBean();
                productImageBean.setImage(img);
                list.add(productImageBean);
            }
        }
        dataBean.setProductImage(list);
        opusVO.setData(dataBean);

        return opusVO;
    }

    @Override
    public PageInfo<OpusResp> selectByObjectListDesc(OpusReq opusReq) {
        Page page = PageHelper.startPage(opusReq.getPageNum(), opusReq.getPageSize());
        List<ArtistOpus> artistOpuses = artistOpusMapper.selectByObjectListDesc(new ArtistOpus());
        Shop shop = new Shop();
        shop.setMallId(1L);
        Map<Long, List<Shop>> map = getShopMap(shop).collect(Collectors.groupingBy(Shop::getId));
        Collections.shuffle(artistOpuses);
        List<OpusResp> resp = new ArrayList<>();
        for (ArtistOpus artistOpus : artistOpuses) {

            OpusResp opusResp = new OpusResp();
            opusResp.setOpusImg(artistOpus.getImage());
            opusResp.setOpusTitle(artistOpus.getTitle());
            opusResp.setLikesNum(artistOpus.getLikesNum());

            Optional<List<Shop>> shopOptional = Optional.ofNullable(map.get(artistOpus.getItemId()));
            if (shopOptional.isPresent() && shopOptional.get().get(0) != null) {
                opusResp.setArtHeadPortrait(shopOptional.get().get(0).getShopLogo());
                opusResp.setArtName(shopOptional.get().get(0).getShopName());
            }
            resp.add(opusResp);
        }
        PageInfo<OpusResp> pageResp = PageInfo.of(resp);
        pageResp.setTotal(page.getTotal());
        return pageResp;
    }

    @Override
    public Wrapper<Boolean> createOpus(CreateOpusReq createOpusReq) {
        Shop shop = new Shop();
        shop.setMallId(1L);
        Map<Long, List<Shop>> map = getShopMap(shop).collect(Collectors.groupingBy(Shop::getId));
        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(createOpusReq.getUserId());
        ArtistOpus artistOpus = new ArtistOpus();
        if (CollectionUtils.isEmpty(userShopRelations)) {
            return WrapMapper.error("请先申请艺术家");
        }
        Set<Long> set = map.keySet();
        Optional<UserShopRelation> userShopRelation = userShopRelations.stream().filter(userShopRelation1 -> set.contains(userShopRelation1.getShopId())).findFirst();
        if (!userShopRelation.isPresent()) {
            return WrapMapper.error("请先申请艺术家");
        }
        if (createOpusReq.getImgs().size() == 0 || createOpusReq.getImgs().size() > 3) {
            return WrapMapper.error("请正确填写图片");
        }

        artistOpus.setTitle(createOpusReq.getTitle());
        artistOpus.setOpusDesc(createOpusReq.getOpusDesc());
        artistOpus.setImage(createOpusReq.getImgs().get(0));
        artistOpus.setImgs(String.join(",", createOpusReq.getImgs()));
        artistOpus.setItemId(userShopRelation.get().getShopId());
        if (createOpusReq.getId() != null) {
            artistOpus.setId(createOpusReq.getId());
            artistOpusMapper.updateByPrimaryKeySelective(artistOpus);
        } else {
            artistOpusMapper.insertSelective(artistOpus);
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<Boolean> delete(DeleteOpusParam deleteOpusParam) {
        Shop shop = new Shop();
        shop.setMallId(1L);
        ArtistOpus artistOpus = artistOpusMapper.selectByPrimaryKey(deleteOpusParam.getId());
        if (artistOpus == null) {
            return WrapMapper.error("未找到数据");
        }
        List<UserShopRelation> userShopRelation = userShopRelationService.selectByUserId(deleteOpusParam.getUserId());
        if(CollectionUtils.isEmpty(userShopRelation)){
            return WrapMapper.error("请先申请艺术家");
        }
        Optional<UserShopRelation> userShopRelation1 = userShopRelation.stream().findFirst();
        if(!userShopRelation1.isPresent() || !userShopRelation1.get().getShopId().equals(artistOpus.getItemId())){
            return WrapMapper.error("不能删除非自己的作品");
        }
        artistOpusMapper.deleteByPrimaryKey(deleteOpusParam.getId());
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<PageInfo<OpusResp>> selectByObjectListOfMyDesc(OpusReq opusReq) {
        List<UserShopRelation> userShopRelations = userShopRelationService.selectByUserId(opusReq.getUserId());
        if (CollectionUtils.isEmpty(userShopRelations)) {
            return WrapMapper.error("请先申请艺术家");
        }
        Shop shop = new Shop();
        shop.setMallId(1L);
        Map<Long, List<Shop>> map = getShopMap(shop).collect(Collectors.groupingBy(Shop::getId));
        if (map.get(userShopRelations.get(0).getShopId()) == null) {
            return WrapMapper.error("请先申请艺术家");
        }
        Optional<Shop> shopOptional = map.get(userShopRelations.get(0).getShopId()).stream().findFirst();
        if (!shopOptional.isPresent()) {
            return WrapMapper.error("请先申请艺术家");
        }
        Shop shop1 = shopOptional.get();
        Page page = PageHelper.startPage(opusReq.getPageNum(), opusReq.getPageSize());
        ArtistOpus artistOpus = new ArtistOpus();
        artistOpus.setItemId(shop1.getId());
        List<ArtistOpus> artistOpuses = artistOpusMapper.selectByObjectListDesc(artistOpus);
        List<OpusResp> resp = new ArrayList<>();
        for (ArtistOpus artistOpus1 : artistOpuses) {

            OpusResp opusResp = new OpusResp();
            opusResp.setOpusImg(artistOpus1.getImage());
            opusResp.setOpusTitle(artistOpus1.getTitle());
            opusResp.setLikesNum(artistOpus1.getLikesNum());
            opusResp.setArtHeadPortrait(shop1.getShopLogo());
            opusResp.setArtName(shop1.getShopName());
            opusResp.setOpusId(artistOpus1.getId());
            resp.add(opusResp);
        }
        PageInfo<OpusResp> pageResp = PageInfo.of(resp);
        pageResp.setTotal(page.getTotal());
        return WrapMapper.ok(pageResp);
    }

    private Stream<Shop> getShopMap(Shop shop) {
        List<Shop> shops = shopService.selectByObjectList(shop);
        return shops.stream().filter(shop1 -> shop1.getShopType() == ShopTypeEnum.artist.getCode() && shop1.getShopStatus() == ShopStatusEnum.success.getCode());
    }


}
