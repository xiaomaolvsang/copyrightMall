package com.copyright.mall.manage.controller;


import com.copyright.mall.bean.Mall;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.manage.domain.dto.MallParam;
import com.copyright.mall.manage.domain.vo.MallResp;
import com.copyright.mall.service.IMallService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.util.UserUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "后端商城管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/mall")
public class ManageMallController {

    @Resource
    private IMallService mallService;

    @Resource
    private IShopService shopService;

    @GetMapping("/getMall")
    @ApiOperation("获取用户下的商城")
    public Wrapper<List<MallResp>> getShop() {
        List<Mall> malls = mallService.selectByObjectList(new Mall());
        List<MallResp> resps = new ArrayList<>();
        malls.forEach(mall -> {
            MallResp mallResp = new MallResp();
            BeanUtils.copyProperties(mall, mallResp);
            resps.add(mallResp);
        });
        return WrapMapper.ok(resps);
    }
}
