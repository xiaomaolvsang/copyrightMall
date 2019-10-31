package com.copyright.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhangyuchen
 * @date : 2019/9/23 15:32
 */
@RestController
public class CheckHealthController {

    @GetMapping("checkHealth")
    public String checkHealth(){
        return "mall is onLine";
    }
}
