package com.copyright.mall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhangyuchen
 * @date : 2019/9/23 15:32
 */
@RestController
public class CheckHealthController {

    @RequestMapping("checkHealth")
    public String checkHealth(){
        return "mall is onLine";
    }
}
