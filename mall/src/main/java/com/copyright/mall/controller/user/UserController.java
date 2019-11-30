package com.copyright.mall.controller.user;

import com.copyright.mall.bean.UserAddress;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.user.CreateAddressParam;
import com.copyright.mall.domain.dto.user.QueryAddressParam;
import com.copyright.mall.domain.vo.user.UserAddressDetailVO;
import com.copyright.mall.domain.vo.user.UserAddressVO;
import com.copyright.mall.domain.vo.user.UserInfoVO;
import com.copyright.mall.service.IUserAddressService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private IUserAddressService userAddressService;


    @PostMapping("/createAddress")
    @ApiOperation(value = "新增收货地址")
    public Wrapper<Boolean> createAddress (@ApiParam @Valid @RequestBody CreateAddressParam createAddressParam){
        UserAddress userAddress = BeanMapperUtils.map(createAddressParam,UserAddress.class);
        userAddress.setUserId(this.getUserId());
        userAddressService.insertSelective(userAddress);
        return WrapMapper.ok();
    }

    @GetMapping("/getAddress")
    @ApiOperation(value = "获取收货地址列表")
    public Wrapper<PageInfo<UserAddressVO>> getAddress(@ApiParam @Valid QueryAddressParam queryAddressParam){
        Page<UserAddress> page = PageHelper.startPage(queryAddressParam.getPageNum(),queryAddressParam.getPageSize());
        UserAddress queryParam = new UserAddress();
        queryParam.setUserId(this.getUserId());
        List<UserAddress> userAddresses = userAddressService.selectByObjectList(queryParam);
        List<UserAddressVO> userAddressVOS = BeanMapperUtils.mapList(userAddresses,UserAddressVO.class);
        PageInfo<UserAddressVO> result = PageInfo.of(userAddressVOS);
        result.setTotal(page.getTotal());
        return WrapMapper.ok(result);
    }

    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    public Wrapper<UserInfoVO> getUserInfo(){
        return WrapMapper.ok();
    }


    @PutMapping("/modifyAddress/{id}")
    public Wrapper<Boolean> modifyAddress(@PathVariable("id") Long id, @ApiParam @Valid @RequestBody CreateAddressParam createAddressParam){
        UserAddress userAddress = BeanMapperUtils.map(createAddressParam,UserAddress.class);
        userAddress.setId(id);
        userAddress.setUserId(this.getUserId());
        userAddressService.updateByPrimaryKeySelective(userAddress);
        return WrapMapper.ok();
    }

    @DeleteMapping("/deleteAddress/{id}")
    public Wrapper<Boolean> deleteAddress(@PathVariable("id") Long id){
        userAddressService.deleteByPrimaryKey(id);
        return WrapMapper.ok();
    }

    @GetMapping("/addressDetail/{id}")
    public Wrapper<UserAddressDetailVO> addressDetail(@PathVariable("id") Long id){
        UserAddress userAddress = userAddressService.selectByPrimaryKey(id);
        if(userAddress==null){
            return WrapMapper.error("地址详情不存在");
        }
        UserAddressDetailVO result = BeanMapperUtils.map(userAddress,UserAddressDetailVO.class);
        return WrapMapper.ok(result);
    }


}
