package com.copyright.mall.controller.goods;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.req.GoodsReq;
import com.copyright.mall.message.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * goodsController
 *
 * @author lijian
 * @version 1.0
 * @date 2019/10/10 4:52 下午
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {


  @RequestMapping("/list")
  @ResponseBody
  @ControllerErro
  public ApiResult test(@RequestBody GoodsReq goodsListReq){

    return null;
  }

}
