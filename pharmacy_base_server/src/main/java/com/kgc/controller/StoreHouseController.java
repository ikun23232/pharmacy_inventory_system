package com.kgc.controller;


import com.kgc.entity.BaseStorehouse;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.StoreHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 10:34
 */
@RestController
@RequestMapping("/base")
public class StoreHouseController {
    @Autowired
    private StoreHouseService service;

    @RequestMapping("/getStoreHouseList")
    public Message getStoreHouseList(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> pageMap = (Map<String, Object>) requestBody.get("page");
        int pageNum = (int) pageMap.get("pageNum");
        int pageSize = (int) pageMap.get("pageSize");
        String code = (String) requestBody.get("code");
        Page page = new Page(pageNum, pageSize);
        Message message = service.getStoreHouseList(code, page);
        return message;
    }

    @RequestMapping("/deleteStorehouse")
    public Message deleteStorehouse(int id) {
        Message message = service.deleteStorehouse(id);
        return message;
    }

    @RequestMapping("/checkName")
    public Message checkName(String name) {
        Message message = service.checkName(name);
        return message;
    }

    @RequestMapping("/addStoreHouse")
    public Message addStoreHouse(@RequestBody BaseStorehouse storeHouse) {
        Message message = service.addStoreHouse(storeHouse);
        return message;
    }

    @RequestMapping("/updateStoreHouse")
    public Message updateStoreHouse(@RequestBody BaseStorehouse storeHouse) {
        Message message = service.updateStoreHouse(storeHouse);
        return message;
    }

    @RequestMapping("/getStoreHouseById")
    public Message getStoreHouseById(int id) {
        Message message = service.getStoreHouseById(id);
        return message;
    }

    @RequestMapping("/getAllStoreHouseList")
    public Message getAllStoreHouseList() {
        Message message = service.getAllStoreHouseList();
        return message;
    }
}
