package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysUser;
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
public class SysUserController {
    @Autowired
    private SysUserService userService;
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
        page.setPageSize(5);
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
    public Message delUnitById(int userid) {
        Message message = userService.delUserById(userid);
        return message;
    }
    @RequestMapping("/updateUser")
    public Message updateUnit(@RequestBody SysUser sysUser) {
        Message message = userService.updateUser(sysUser);
        return message;
    }
    @RequestMapping("/saveUser")
    public Message saveUser(@RequestBody SysUser sysUser) {
        boolean save = userService.save(sysUser);
        if (!save){
            return Message.error("添加失败");
        }
        return Message.success(save);
    }
    @RequestMapping("/selectUser")
    public Message selectUser(){
        Message message = userService.selectUser();
        return message;
    }
}

