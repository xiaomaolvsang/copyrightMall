package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.manage.domain.dto.LoginParam;
import com.copyright.mall.manage.domain.dto.QueryGoodsParam;
import com.copyright.mall.manage.domain.vo.LoginRes;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "后端登陆管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/login")
public class ManageLoginController {
    @PostMapping("/login")
    @ApiOperation("登陆")
    public Wrapper<LoginRes> getGoods(@ApiParam @Valid @RequestBody LoginParam loginParam) {
        log.info("getShop = {}", loginParam);
        return WrapMapper.ok();
    }
}
