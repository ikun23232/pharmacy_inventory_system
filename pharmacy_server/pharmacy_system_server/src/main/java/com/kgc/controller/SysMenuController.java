package com.kgc.controller;


import com.kgc.entity.Message;
import com.kgc.entity.SysUser;
import com.kgc.service.SysMenuService;
import com.kgc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/user")
public class SysMenuController {

    @Autowired
    private SysMenuService  sysMenuService;
    @RequestMapping("/menu/nav")
    public Message nav(){
        Message message = sysMenuService.getAuth();
        return message;
    }

    @RequestMapping("/menu/list")
    public Message list(){
        Message message = sysMenuService.getAllMenuList();
        return message;
    }

}

