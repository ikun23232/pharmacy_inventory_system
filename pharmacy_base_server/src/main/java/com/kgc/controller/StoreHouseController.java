package com.kgc.controller;


import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.StoreHouse;
import com.kgc.service.StoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 10:34
 */
@RestController
@RequestMapping("storehouse")
public class StoreHouseController {

    @Autowired
    private StoreHouseService service;

    @RequestMapping("getStoreHouseList")
    public Message getStoreHouseList(String code, Page page) {
        Message message = service.getStoreHouseList(code,page);
        return message;
    }

    @RequestMapping("deleteStorehouse")
    public Message deleteStorehouse(int id) {
        Message message = service.deleteStorehouse(id);
        return message;
    }

    @RequestMapping("checkName")
    public Message checkName(String name) {
        Message message = service.checkName(name);
        return message;
    }

    @RequestMapping("addStoreHouse")
    public Message addStoreHouse(StoreHouse storeHouse) {
        Message message = service.addStoreHouse(storeHouse);
        return message;
    }

    @RequestMapping("updateStoreHouse")
    public Message updateStoreHouse(StoreHouse storeHouse) {
        Message message = service.updateStoreHouse(storeHouse);
        return message;
    }
}
