package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kgc.annotation.LoginLog;
import com.kgc.entity.*;
import com.kgc.service.SysRoleService;
import com.kgc.service.SysUserService;
import com.kgc.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class SysUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysRoleService sysRoleService;
    @LoginLog("登录")
    @RequestMapping("/login")
    public Message login(@RequestBody SysUser loginForm){
        Message message = userService.login(loginForm);
        return message;
    }
    @RequestMapping("/getUserListByPage")
    public Message getUserListByPage(String username,Integer sex,Integer isstate,String currentNo){
            int _currentPageNo = 1;

        if (currentNo!=null&&!"".equals(currentNo)) {
            _currentPageNo = Integer.parseInt(currentNo);
        }
        Page page = new Page();
        page.setPageSize(1);
        page.setCurrentPageNo(_currentPageNo);
        Message message = userService.getUsersListByPage(username,sex,isstate,page);
        return message;
    }
    @RequestMapping("logout")
    public Message logout() {
        StpUtil.logout();
        return Message.success();
    }
    @RequestMapping("/delUserById")
    public Message delUnitById(@RequestBody Integer[] ids) {
        Message message = userService.delUserById(ids);
        return message;
    }
    @RequestMapping("/updateUser")
    public Message updateUnit(@RequestBody SysUser sysUser) {
        Message message = userService.updateUser(sysUser);
        return message;
    }
    @RequestMapping("/saveUser")
    public Message saveUser(@RequestBody SysUser sysUser) {
        sysUser.setCreatedate(new Date());
        String password="123456";
        String md5String = Md5Util.getMD5String(password);
        sysUser.setPassword(md5String);
        userService.save(sysUser);
        return Message.success(sysUser);
    }
    @RequestMapping("/selectUser")
    public Message selectUser(){
        Message message = userService.selectUser();
        return message;
    }

    @RequestMapping("/info/{id}")
    public Message info(@PathVariable("id") Integer id) {

        SysUser sysUser = userService.getById(id);
        Assert.notNull(sysUser, "找不到该管理员");

        List<SysRole> roles = sysRoleService.listRolesByUserId(id);

        sysUser.setSysRoles(roles);
        return Message.success(sysUser);
    }

    @RequestMapping("/role/{userId}")
    public Message rolePerm(@PathVariable("userId") Integer userId, @RequestBody Integer[] roleIds) {
        Message message = userService.rolePerm(userId, roleIds);
        return message;
    }
    @RequestMapping("/checkUserName")
    public Message checkUserName(@RequestParam("username") String username,@RequestParam("id")Integer id) {
        Message message = userService.existUser(username,id);
        return message;
    }

    @RequestMapping("/repass")
    public Message repass(Integer userId) {
        Message message = userService.repass(userId);
        return message;
    }
}

