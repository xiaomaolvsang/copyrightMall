package com.copyright.mall.service.impl;

import com.copyright.mall.bean.*;
import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.dao.ArtistOpusMapper;
import com.copyright.mall.dao.CollectionUserMapper;
import com.copyright.mall.dao.LikeOpusRelationMapper;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.opus.*;
import com.copyright.mall.domain.vo.opus.OpusResp;
import com.copyright.mall.domain.vo.opus.OpusResp.OpusGoods;
import com.copyright.mall.domain.vo.opus.OpusVO;
import com.copyright.mall.manage.domain.dto.OpusDelParam;
import com.copyright.mall.manage.domain.dto.OpusManageParam;
import com.copyright.mall.manage.domain.dto.OpusUpdateParam;
import com.copyright.mall.manage.domain.vo.OpusManageResp;
import com.copyright.mall.service.IOpusService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.util.ListUtil;
import com.copyright.mall.util.TimeUtil;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private LikeOpusRelationMapper likeOpusRelationMapper;

    @Resource
    private ItemService itemService;

    @Resource
    private CollectionUserMapper collectionUserMapper;

    @Override
    public OpusVO getOpus(OpusParam opusParam) {
        OpusVO opusVO = new OpusVO();
        OpusVO.DataBean dataBean = new OpusVO.DataBean();

        ArtistOpus artistOpus = artistOpusMapper.selectByPrimaryKey(opusParam.getOpusId());
        if (artistOpus == null) {
            throw new BusinessException("未找到任何数据");
        }
        Shop shop = shopService.selectByPrimaryKey(artistOpus.getItemId());

        List<Item> items = itemService.selectAll();
        Map<Long, List<Item>> itemsMap = items.stream().filter(item -> item.getItemStatus() == 1).collect(Collectors.groupingBy(Item::getId));
        CollectionUser collectionUser = new CollectionUser();
        collectionUser.setUserId(opusParam.getUserId());
        List<CollectionUser> collectionUsers = collectionUserMapper.selectByObjectList(collectionUser);
        List<Long> opusIds;
        if (collectionUsers != null && collectionUsers.size() > 0) {
            opusIds = collectionUsers.stream().map(CollectionUser::getOpusId).collect(Collectors.toList());
        } else {
            opusIds = new ArrayList<>();
        }

        List<OpusVO.DataBean.ProductImageBean> list = new ArrayList<>();

        dataBean.setArtistAvatar(shop.getShopLogo());
        dataBean.setArtistName(shop.getShopName());
        dataBean.setArtistId(shop.getId());
        dataBean.setProductDesc(artistOpus.getOpusDesc());
        dataBean.setProductTitle(artistOpus.getTitle());
        dataBean.setPublishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(artistOpus.getGmtModified()));
        dataBean.setIfCollection(opusIds.contains(opusParam.getOpusId()));
        if (itemsMap != null && itemsMap.get(artistOpus.getGoodsId()) != null) {
            Optional<Item> optional = itemsMap.get(artistOpus.getGoodsId()).stream().findFirst();
            if (optional.isPresent()) {
                Item item = optional.get();
                OpusResp.OpusGoods opusGoods = new OpusResp.OpusGoods();
                opusGoods.setGoodsImg(item.getTitleImg());
                opusGoods.setGoodsName(item.getItemTitle());
                opusGoods.setPrice(item.getPrice());
                opusGoods.setShopName(shop.getShopName());
                opusGoods.setGoodsId(item.getId());
                dataBean.setOpusGoods(opusGoods);
            }
        }

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
        //点赞数
        LikeOpusRelation likeOpusRelation = new LikeOpusRelation();
        likeOpusRelation.setOpusId(opusParam.getOpusId());
        Long likeNum = likeOpusRelationMapper.selectObjectListPageTotal(likeOpusRelation);
        dataBean.setLikeNum(likeNum == null ? 0L : likeNum);
        return opusVO;
    }

    @Override
    public PageInfo<OpusResp> selectByObjectListDesc(OpusReq opusReq) {
        Page page = PageHelper.startPage(opusReq.getPageNum(), opusReq.getPageSize());
        List<ArtistOpus> artistOpuses = artistOpusMapper.selectByObjectListDesc(new ArtistOpus());
        List<Item> items = itemService.selectAll();
        Map<Long, List<Item>> itemsMap = items.stream().collect(Collectors.groupingBy(Item::getId));
        CollectionUser collectionUser = new CollectionUser();
        collectionUser.setUserId(opusReq.getUserId());
        List<CollectionUser> collectionUsers = collectionUserMapper.selectByObjectList(collectionUser);
        List<Long> opusIds;
        if (collectionUsers != null && collectionUsers.size() > 0) {
            opusIds = collectionUsers.stream().map(CollectionUser::getOpusId).collect(Collectors.toList());
        } else {
            opusIds = new ArrayList<>();
        }
        Shop shop = new Shop();
        shop.setMallId(1L);
        Map<Long, List<Shop>> map = getShopMap(shop).collect(Collectors.groupingBy(Shop::getId));
        Collections.shuffle(artistOpuses, new Random(opusReq.getUserId()));
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
            opusResp.setOpusId(artistOpus.getId());
            opusResp.setOpusDesc(artistOpus.getOpusDesc());
            if (itemsMap != null && itemsMap.get(artistOpus.getGoodsId()) != null) {
                Optional<Item> optional = itemsMap.get(artistOpus.getGoodsId()).stream().findFirst();
                if (optional.isPresent() && shopOptional.isPresent()) {
                    Item item = optional.get();
                    OpusResp.OpusGoods opusGoods = new OpusResp.OpusGoods();
                    opusGoods.setGoodsImg(item.getTitleImg());
                    opusGoods.setGoodsName(item.getItemTitle());
                    opusGoods.setPrice(item.getPrice());
                    opusGoods.setShopName(shopOptional.get().get(0).getShopName());
                    opusResp.setOpusGoods(opusGoods);
                }
            }
            if (opusIds.contains(artistOpus.getId())) {
                opusResp.setIfCollection(true);
            } else {
                opusResp.setIfCollection(false);
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
        if (CollectionUtils.isEmpty(userShopRelation)) {
            return WrapMapper.error("请先申请艺术家");
        }
        Optional<UserShopRelation> userShopRelation1 = userShopRelation.stream().findFirst();
        if (!userShopRelation1.isPresent() || !userShopRelation1.get().getShopId().equals(artistOpus.getItemId())) {
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
        List<Item> items = itemService.selectAll();
        Map<Long, List<Item>> itemsMap = items.stream().collect(Collectors.groupingBy(Item::getId));
        Page page = PageHelper.startPage(opusReq.getPageNum(), opusReq.getPageSize());
        ArtistOpus artistOpus = new ArtistOpus();
        artistOpus.setItemId(shop1.getId());
        List<ArtistOpus> artistOpuses = artistOpusMapper.selectByObjectListDesc(artistOpus);
        List<OpusResp> resp = new ArrayList<>();
        for (ArtistOpus artistOpus1 : artistOpuses) {
            OpusResp opusResp = new OpusResp();
            String imgs = artistOpus1.getImgs();
            List<String> list = Arrays.asList(imgs.split(","));
            opusResp.setOpusImg(artistOpus1.getImage());
            opusResp.setOpusTitle(artistOpus1.getTitle());
            opusResp.setLikesNum(artistOpus1.getLikesNum());
            opusResp.setArtHeadPortrait(shop1.getShopLogo());
            opusResp.setArtName(shop1.getShopName());
            opusResp.setOpusId(artistOpus1.getId());
            opusResp.setOpusDesc(artistOpus1.getOpusDesc());
            opusResp.setOpusImgs(list);
            if (itemsMap != null && itemsMap.get(artistOpus.getGoodsId()) != null) {
                Optional<Item> optional = itemsMap.get(artistOpus.getGoodsId()).stream().findFirst();
                if (optional.isPresent() && shopOptional.isPresent()) {
                    Item item = optional.get();
                    OpusResp.OpusGoods opusGoods = new OpusResp.OpusGoods();
                    opusGoods.setGoodsImg(item.getTitleImg());
                    opusGoods.setGoodsName(item.getItemTitle());
                    opusGoods.setPrice(item.getPrice());
                    opusGoods.setShopName(shopOptional.get().getShopName());
                    opusResp.setOpusGoods(opusGoods);
                }
            }
            resp.add(opusResp);
        }
        PageInfo<OpusResp> pageResp = PageInfo.of(resp);
        pageResp.setTotal(page.getTotal());
        return WrapMapper.ok(pageResp);
    }

    @Override
    public Wrapper<PageInfo<OpusManageResp>> selectManageByObjectList(OpusManageParam opusManageParam) {
        Shop shop = new Shop();
        shop.setMallId(1L);
        List<Shop> shops = shopService.selectByObjectList(shop);
        if (CollectionUtils.isEmpty(shops)) {
            return WrapMapper.ok();
        }
        List<Shop> manageShop = shops.stream().filter(s -> {
            if (opusManageParam.getArtistId() == null && StringUtils.isEmpty(opusManageParam.getArtistName())) {
                return true;
            } else if (opusManageParam.getArtistId() != null && opusManageParam.getArtistId().equals(s.getId())) {
                if (StringUtils.isEmpty(opusManageParam.getArtistName())) {
                    return true;
                } else return s.getShopName().contains(opusManageParam.getArtistName());
            } else if (StringUtils.isNotEmpty(opusManageParam.getArtistName())) {
                return s.getShopName().contains(opusManageParam.getArtistName());
            }
            return false;
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(manageShop)) {
            return WrapMapper.ok();
        }

        Map<Long, String> shopMap = manageShop.stream().collect(Collectors.toMap(Shop::getId, Shop::getShopName));
        List<Long> shopIds = new ArrayList<>(shopMap.keySet());
        Page page = PageHelper.startPage(opusManageParam.getPageNum(), opusManageParam.getPageSize());
        List<ArtistOpus> opuses = artistOpusMapper.selectByShopIds(shopIds, opusManageParam.getOpusTitle());
        List<OpusManageResp> list = new ArrayList<>();
        for (ArtistOpus artistOpus : opuses) {
            OpusManageResp opusManageResp = new OpusManageResp();
            opusManageResp.setArtistId(artistOpus.getItemId());
            opusManageResp.setId(artistOpus.getId());
            opusManageResp.setArtistName(shopMap.get(artistOpus.getItemId()));
            opusManageResp.setCreateDate(TimeUtil.formatDate(artistOpus.getGmtCreate()));
            opusManageResp.setModifyDate(TimeUtil.formatDate(artistOpus.getGmtModified()));
            opusManageResp.setOpusContent(artistOpus.getOpusDesc());
            opusManageResp.setOpusImgs(Arrays.asList(artistOpus.getImgs().split(",")));
            opusManageResp.setOpusName(artistOpus.getTitle());
            list.add(opusManageResp);
        }
        PageInfo<OpusManageResp> pageResp = PageInfo.of(list);
        pageResp.setTotal(page.getTotal());
        return WrapMapper.ok(pageResp);
    }

    private Stream<Shop> getShopMap(Shop shop) {
        List<Shop> shops = shopService.selectByObjectList(shop);
        return shops.stream().filter(shop1 -> shop1.getShopType() == ShopTypeEnum.artist.getCode() && shop1.getShopStatus() == ShopStatusEnum.success.getCode());
    }

    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Boolean> likeIt(LikeOpusParam likeOpusParam) {
        LikeOpusRelation likeOpusRelation = new LikeOpusRelation();
        likeOpusRelation.setOpusId(likeOpusParam.getId());
        likeOpusRelation.setUserId(likeOpusParam.getUserId());
        List<LikeOpusRelation> likeOpusRelations = likeOpusRelationMapper.selectByObjectList(likeOpusRelation);
        if (!CollectionUtils.isEmpty(likeOpusRelations)) {
            return WrapMapper.error("已点赞");
        }
        likeOpusRelationMapper.insertSelective(likeOpusRelation);
        artistOpusMapper.likeOpus(likeOpusParam.getId());
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<Boolean> manageUpdate(OpusUpdateParam opusUpdateParam) {
        ArtistOpus artistOpus = new ArtistOpus();
        artistOpus.setId(opusUpdateParam.getId());
        if (!CollectionUtils.isEmpty(opusUpdateParam.getOpusImgs())) {
            artistOpus.setImgs(String.join(",", opusUpdateParam.getOpusImgs()));
        }
        artistOpus.setTitle(opusUpdateParam.getOpusTitle());
        artistOpus.setOpusDesc(opusUpdateParam.getOpusContent());
        artistOpusMapper.updateByPrimaryKeySelective(artistOpus);
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<Boolean> delOpus(OpusDelParam opusDelParam) {
        artistOpusMapper.deleteByPrimaryKey(opusDelParam.getId());
        return WrapMapper.ok();
    }

}
