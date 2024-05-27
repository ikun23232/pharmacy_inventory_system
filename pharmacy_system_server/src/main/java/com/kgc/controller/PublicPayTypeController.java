package com.kgc.controller;

import com.kgc.dao.PublicPayTypeMapper;
import com.kgc.entity.CgType;
import com.kgc.entity.Message;
import com.kgc.service.PublicPayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/10 17:10
 */
@RestController
@RequestMapping("/user")
public class PublicPayTypeController {

    @Autowired
    private PublicPayTypeService service;

    @RequestMapping("/menus/getPayType")
    public Message getPayType() {
        Message message = service.getPayType();
        return message;
    }
}
