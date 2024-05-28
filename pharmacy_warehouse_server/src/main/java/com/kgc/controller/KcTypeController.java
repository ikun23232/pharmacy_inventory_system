package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.KcTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-05-22 10:53
 **/
@RestController
@RequestMapping("/warehouse")
public class KcTypeController {

    @Autowired
    private KcTypeService kcTypeService;
    @RequestMapping("/getKcTypeList")
    public Message getKcTypeList(){
        Message kcTypeList = kcTypeService.getKcTypeList();
        return kcTypeList;
    }
}
