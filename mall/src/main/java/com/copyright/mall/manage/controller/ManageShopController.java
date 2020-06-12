package com.copyright.mall.manage.controller;

import com.copyright.mall.manage.domain.dto.ModifyShopParam;
import com.copyright.mall.manage.domain.dto.QueryShopParam;
import com.copyright.mall.manage.domain.vo.ShopListRes;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.util.UserUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "后端商铺管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/shop")
public class ManageShopController extends BaseManageController{

    @Resource
    private IShopService iShopService;

    @PostMapping("/getShop")
    @ApiOperation("获取用户下的商铺")
    public Wrapper<PageInfo<ShopListRes>> getShop(@ApiParam @Valid @RequestBody QueryShopParam queryShopParam) {
        log.info("getShop = {}", queryShopParam);
        queryShopParam.setUserId(UserUtils.getUserId());
        return iShopService.getShopListByUserId(queryShopParam);
    }

    @PostMapping("/createShop")
    @ApiOperation("创建商铺")
    public Wrapper<Boolean> createShop(@ApiParam @Valid @RequestBody ModifyShopParam modifyShopParam) {
        log.info("createShop = {}", modifyShopParam);
        modifyShopParam.setMallId(UserUtils.getMallId());
        return iShopService.insertOrUpdateByParam(modifyShopParam);
    }

    @PostMapping("/updateShop")
    @ApiOperation("修改商铺")
    public Wrapper<Boolean> updateShop(@ApiParam @Valid @RequestBody ModifyShopParam modifyShopParam) {
        log.info("updateShop = {}", modifyShopParam);
        return iShopService.insertOrUpdateByParam(modifyShopParam);
    }

}
