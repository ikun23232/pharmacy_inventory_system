package com.kgc.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.Message;
import com.kgc.service.BaseProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/base/baseProvider")
public class BaseProviderController {

    @Autowired
    private BaseProviderService baseProviderService;


    @RequestMapping("getBaseProviderList")
    public Message getBaseProviderList(@RequestParam("name") String name, @RequestParam("type") int type, @RequestParam("currentPageNo") int currentPageNo, @RequestParam("pageSize") int pageSize) {
        Message message = baseProviderService.getBaseProviderList(name, type, currentPageNo, pageSize);
        return message;
    }

    @RequestMapping("addBaseProvider")
    public Message addBaseProvider(BaseProvider baseProvider) {
        Message message = baseProviderService.addBaseProvider(baseProvider);
        return message;
    }

    @RequestMapping("updateBaseProvider")
    public Message updateBaseProvider(BaseProvider baseProvider) {
        Message message = baseProviderService.updateBaseProvider(baseProvider);
        return message;
    }

    @RequestMapping("delBaseProvider")
    public Message delBaseProvider(@RequestParam("id") Integer id) {
        Message message = baseProviderService.delBaseProvider(id);
        return message;
    }

    @RequestMapping("getBaseProviderById")
    public Message getBaseProviderById(@RequestParam("id")int id) {
        Message message = baseProviderService.getBaseProviderById(id);
        return message;
    }

    @RequestMapping("checkaddBaseProvider")
    public Message checkaddBaseProvider(@RequestParam("name")String name) {
        Message message = baseProviderService.checkaddBaseProvider(name);
        return message;
    }

    @RequestMapping("checkupdateBaseProvider")
    public Message checkupdateBaseProvider(@RequestParam("name") String name,@RequestParam("id")int id) {
        Message message = baseProviderService.checkupdateBaseProvider(name,id);
        return message;
    }
}

