package com.kgc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kgc.entity.Message;
import com.kgc.entity.SysMenu;
import com.kgc.entity.SysRolemenu;
import com.kgc.entity.SysUser;
import com.kgc.service.SysMenuService;
import com.kgc.service.SysRoleMenuService;
import com.kgc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;

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
    @Autowired
    private SysUserService  sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    @RequestMapping("/menu/nav")
    public Message nav(){
        Message message = sysMenuService.getAuth();
        return message;
    }

    @RequestMapping("/menu/info/{id}")
    public Message info(@PathVariable(name = "id") Integer id) {
        return Message.success(sysMenuService.getById(id));
    }

    @RequestMapping("/menu/list")
    public Message list(){
        Message message = sysMenuService.getAllMenuList();
        return message;
    }
    @RequestMapping("/menu/save")
    public Message save(@Validated @RequestBody SysMenu sysMenu) {

        sysMenu.setCreatedate(new Date());

        sysMenuService.save(sysMenu);
        return Message.success(sysMenu);
    }

    @RequestMapping("/menu/update")

    public Message update(@Validated @RequestBody SysMenu sysMenu) {

        sysMenu.setUpdatedate(new Date());

        sysMenuService.updateById(sysMenu);

        // 清除所有与该菜单相关的权限缓存
        sysUserService.clearUserAuthorityInfoByMenuId(sysMenu.getId());
        return Message.success(sysMenu);
    }

    @RequestMapping("/menu/delete/{id}")
    public Message delete(@PathVariable("id") Integer id) {

        int count = sysMenuService.count(new QueryWrapper<SysMenu>().eq("parentId", id));
        if (count > 0) {
            return Message.error("请先删除子菜单");
        }

        // 清除所有与该菜单相关的权限缓存
        sysUserService.clearUserAuthorityInfoByMenuId(id);

        sysMenuService.removeById(id);

        // 同步删除中间关联表
        sysRoleMenuService.remove(new QueryWrapper<SysRolemenu>().eq("menuId", id));
        return Message.success("");
    }
}

