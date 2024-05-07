package com.kgc.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.entity.BaseProvider;
import com.kgc.entity.Message;
import com.kgc.service.BaseProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/api/baseProvider")
public class BaseProviderController {

    @Autowired
    private BaseProviderService baseProviderService;


    @PostMapping("/getBaseProviderList")
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
    public Message delBaseProvider(int id) {
        Message message = baseProviderService.delBaseProvider(id);
        return message;
    }

    @RequestMapping("getBaseProviderById")
    public Message getBaseProviderById(int id) {
        Message message = baseProviderService.getBaseProviderById(id);
        return message;
    }
}

