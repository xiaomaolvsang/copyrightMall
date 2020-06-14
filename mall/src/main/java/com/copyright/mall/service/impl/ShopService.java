package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.User;
import com.copyright.mall.bean.UserShopRelation;
import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import com.copyright.mall.config.GuavaManage;
import com.copyright.mall.dao.ShopMapper;
import com.copyright.mall.dao.UserMapper;
import com.copyright.mall.manage.domain.dto.ModifyShopParam;
import com.copyright.mall.manage.domain.dto.QueryGoodsParam;
import com.copyright.mall.manage.domain.dto.QueryShopParam;
import com.copyright.mall.manage.domain.vo.ShopListRes;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.IUserShopRelationService;
import com.copyright.mall.util.MD5Util;
import com.copyright.mall.util.TimeUtil;
import com.copyright.mall.util.UserUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 商铺表
 *
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class ShopService implements IShopService {

    private static Logger logger = LoggerFactory.getLogger(ShopService.class);

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private GuavaManage guavaManage;

    @Resource
    private IUserShopRelationService iUserShopRelationService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private IUserService iUserService;

    @Override
    public Shop selectByPrimaryKey(Long id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {

        return shopMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Shop shop) {
        return shopMapper.insertSelective(shop);
    }

    @Override
    public int updateByPrimaryKeySelective(Shop shop) {
        return shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public Long selectObjectListPageTotal(Shop shop) {
        return shopMapper.selectObjectListPageTotal(shop);
    }

    @Override
    public List<Shop> selectObjectListPage(Shop shop) {
        return shopMapper.selectObjectListPage(shop);
    }

    @Override
    public List<Shop> selectByObjectList(Shop shop) {
        Optional<Object> infoOptional = guavaManage.getCache(getKey(shop.getMallId()),
                () -> Optional.ofNullable(shopMapper.selectByObjectList(shop)));
        List<Shop> result = new ArrayList<>();
        if (infoOptional.isPresent()) {
            result = (List<Shop>) infoOptional.get();
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Boolean> insertOrUpdateByParam(ModifyShopParam modifyShopParam) {
        Shop shop = new Shop();
        shop.setId(modifyShopParam.getShopId());
        shop.setShopName(modifyShopParam.getShopName());
        shop.setMallId(modifyShopParam.getMallId());
        shop.setShopLogo(modifyShopParam.getShopLogo());
        shop.setCompanyName(modifyShopParam.getCompanyName());
        shop.setCertification(modifyShopParam.getCertification());
        shop.setShopImg(modifyShopParam.getShopImg());
        shop.setShopArtcategory(modifyShopParam.getShopArtCategory());
        shop.setIsIdentification(modifyShopParam.getIsIdentification());
        shop.setShopType(modifyShopParam.getShopType());
        shop.setShopStatus(ShopStatusEnum.success.getCode());
        if (shop.getId() != null) {
            shopMapper.updateByPrimaryKeySelective(shop);
        } else {
            shopMapper.insertSelective(shop);
        }
        //删除关系表
        UserShopRelation userShopRelation = new UserShopRelation();
        userShopRelation.setShopId(shop.getId());
        List<UserShopRelation> userShopRelations = iUserShopRelationService.selectByObjectList(userShopRelation);
        List<String> userList = modifyShopParam.getUsers().stream().map(ModifyShopParam.User::getUserId).collect(Collectors.toList());
        userShopRelations.stream().filter(userShopRelation1 -> !userList.contains(userShopRelation1.getUserId().toString())).forEach(userShopRelation1 -> {
            iUserShopRelationService.deleteByPrimaryKey(userShopRelation1.getId());
        });

        modifyShopParam.getUsers().forEach(user -> {
            if (StringUtils.isNotEmpty(user.getPassWord())) {
                //添加关系
                UserShopRelation newUserShopRelation = new UserShopRelation();
                newUserShopRelation.setShopId(shop.getId());
                newUserShopRelation.setUserId(Long.valueOf(user.getUserId()));
                iUserShopRelationService.insertSelective(newUserShopRelation);
                //修改密码
                User user1 = new User();
                user1.setId(Long.valueOf(user.getUserId()));
                user1.setPassword(MD5Util.digest(user.getPassWord()));
                userMapper.updateByPrimaryKeySelective(user1);
            }
        });
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<PageInfo<ShopListRes>> getShopListByUserId(QueryShopParam queryShopParam) {
        //管理员
        List<ShopListRes> shopListResList = new ArrayList<>();

        List<Shop> shops = new ArrayList<>();
        Page page ;
        if (UserUtils.isAdmin()) {
            Shop shop = new Shop();
            if (queryShopParam.getShopId() != null) {
                shop.setId(queryShopParam.getShopId());
            }
            if (StringUtils.isNotEmpty(queryShopParam.getShopName())) {
                shop.setShopName(queryShopParam.getShopName());
            }
            if (queryShopParam.getShopType() != null) {
                shop.setShopType(queryShopParam.getShopType());
            }
            page = PageHelper.startPage(queryShopParam.getPageNum(), queryShopParam.getPageSize());
            shops = shopMapper.selectByObjectList(shop);
        } else {
            UserShopRelation userShopRelation = new UserShopRelation();
            userShopRelation.setUserId(UserUtils.getUserId());
            List<UserShopRelation> list = iUserShopRelationService.selectByObjectList(userShopRelation);
            List<Long> shopIds = list.stream().map(UserShopRelation::getShopId).collect(Collectors.toList());
            page = PageHelper.startPage(queryShopParam.getPageNum(), queryShopParam.getPageSize());
            shops = shopMapper.selectByShopIds(shopIds);
        }

        shops.forEach(shop -> {
            ShopListRes shopListRes = new ShopListRes();
            shopListRes.setShopName(shop.getShopName());
            shopListRes.setShopLogo(shop.getShopLogo());
            shopListRes.setShopType(shop.getShopType());
            shopListRes.setCompanyName(shop.getCompanyName());
            shopListRes.setCertification(shop.getCertification());
            shopListRes.setShopImg(shop.getShopImg());
            shopListRes.setShopArtCategory(shop.getShopArtcategory());
            shopListRes.setIsIdentification(shop.getIsIdentification());
            shopListRes.setCreateTime(TimeUtil.formatDate(shop.getGmtCreate()));
            shopListRes.setShopId(shop.getId());
            List<ShopListRes.User> users = new ArrayList<>();
            UserShopRelation userShopRelation = new UserShopRelation();
            userShopRelation.setShopId(shop.getId());
            List<UserShopRelation> list = iUserShopRelationService.selectByObjectList(userShopRelation);
            list.forEach(userShopRelation1 -> {
                User user = iUserService.selectByUserId(userShopRelation1.getUserId());
                ShopListRes.User userRes = new ShopListRes.User();
                userRes.setPhone(user.getPhone());
                userRes.setUserId(user.getId());
                users.add(userRes);
            });
            shopListRes.setUsers(users);
            shopListResList.add(shopListRes);
        });
        PageInfo pageInfo = PageInfo.of(shopListResList);
                pageInfo.setTotal(page.getTotal());
        return WrapMapper.ok(pageInfo);
    }

    private String getKey(Long id) {
        return "shop" + id;
    }


}
