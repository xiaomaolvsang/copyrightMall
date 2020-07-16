package com.copyright.mall.controller.collection;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.ArtistOpus;
import com.copyright.mall.bean.CollectionUser;
import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.dao.ArtistOpusMapper;
import com.copyright.mall.dao.CollectionUserMapper;
import com.copyright.mall.domain.requeest.collection.CollectionCreateParam;
import com.copyright.mall.domain.requeest.collection.CollectionParam;
import com.copyright.mall.domain.vo.opus.OpusResp;
import com.copyright.mall.service.IItemService;
import com.copyright.mall.service.IOpusService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Api(tags = {"收藏"})
@RequestMapping("/collection")
@Slf4j
public class CollectionUserController extends BaseController {

    @Resource
    private CollectionUserMapper collectionUserMapper;

    @Resource
    private IOpusService opusService;

    @Resource
    private ArtistOpusMapper artistOpusMapper;

    @Resource
    private IItemService iItemService;

    @Resource
    private IShopService iShopService;

    @ApiOperation(value = "收藏列表")
    @PostMapping("/collectionList")
    @ControllerErro
    public Wrapper<PageInfo<OpusResp>> collection(@RequestBody @ApiParam @Valid CollectionParam collectionParam){
        collectionParam.setUserId(getUserId());
        CollectionUser collectionUser = new CollectionUser();
        collectionUser.setUserId(collectionParam.getUserId());
        List<CollectionUser> collectionUsers = collectionUserMapper.selectByObjectList(collectionUser);
        if(CollectionUtils.isEmpty(collectionUsers)){
              return  WrapMapper.ok(PageInfo.of(new ArrayList<>()));
        }
        List<Long> opusList = collectionUsers.stream().map(CollectionUser::getOpusId).collect(Collectors.toList());

        Page page = PageHelper.startPage(collectionParam.getPageNum(), collectionParam.getPageSize());
        List<ArtistOpus> artistOpuses = artistOpusMapper.selectByOpusIds(opusList);
        List<Item> items = iItemService.selectAll();
        Map<Long, List<Item>> itemsMap = items.stream().collect(Collectors.groupingBy(Item::getId));
        Shop shop = new Shop();
        shop.setMallId(1L);
        Map<Long, List<Shop>> map = getShopMap(shop).collect(Collectors.groupingBy(Shop::getId));
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
            resp.add(opusResp);
        }
        PageInfo<OpusResp> pageResp = PageInfo.of(resp);
        pageResp.setTotal(page.getTotal());
        return WrapMapper.ok(pageResp);
    }

    private Stream<Shop> getShopMap(Shop shop) {
        List<Shop> shops = iShopService.selectByObjectList(shop);
        return shops.stream().filter(shop1 -> shop1.getShopType() == ShopTypeEnum.artist.getCode() && shop1.getShopStatus() == ShopStatusEnum.success.getCode());
    }


    @ApiOperation(value = "收藏")
    @PostMapping("/collection")
    @ControllerErro
    public Wrapper<Boolean> createCollection(@RequestBody @ApiParam @Valid CollectionCreateParam collectionCreateParam){
        CollectionUser collectionUser = new CollectionUser();
        collectionUser.setUserId(getUserId());
        collectionUser.setOpusId(collectionCreateParam.getOpusId());
        try {
            collectionUserMapper.insertSelective(collectionUser);
        }catch (Exception e){

        }
        return WrapMapper.ok();
    }

    @ApiOperation(value = "取消收藏")
    @PostMapping("/delCollection")
    @ControllerErro
    public Wrapper<Boolean> delCollection(@RequestBody @ApiParam @Valid CollectionCreateParam collectionCreateParam){
        collectionUserMapper.deleteByUserIdAndOpusId(getUserId(),collectionCreateParam.getOpusId());
        return WrapMapper.ok();
    }
}
