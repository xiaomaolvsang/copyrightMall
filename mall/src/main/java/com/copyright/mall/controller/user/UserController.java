package com.copyright.mall.controller.user;

import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.user.CreateAddressParam;
import com.copyright.mall.domain.vo.user.UserAddressVO;
import com.copyright.mall.domain.vo.user.UserInfoVO;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:45
 */
@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {


    @PostMapping("/createAddress")
    @ApiOperation(value = "新增收货地址")
    public Wrapper<Boolean> createAddress (@ApiParam @Valid @RequestBody CreateAddressParam createAddressParam){
        return WrapMapper.ok();
    }

    @GetMapping("/getAddress")
    @ApiOperation(value = "获取收货地址")
    public Wrapper<List<UserAddressVO>> getAddress(){
        return WrapMapper.ok();
    }

    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    public Wrapper<UserInfoVO> getUserInfo(){
        return WrapMapper.ok();
    }


}
