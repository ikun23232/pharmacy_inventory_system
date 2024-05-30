package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.kgc.annotation.Log;
import com.kgc.annotation.LoginLog;
import com.kgc.entity.*;
import com.kgc.service.SysRoleService;
import com.kgc.service.SysUserService;
import com.kgc.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping("/existLogin")
    public Message existLogin(@RequestBody SysUser loginForm){
        Message message = userService.existLogin(loginForm);
        return message;
    }
    @Log("修改密码")
    @RequestMapping("/updatePass")
    public Message updatePass( String password){
        Message message = userService.updatePass(password);
        return message;
    }

    @RequestMapping("/getUserListByPage")
    public Message getUserListByPage(String username,Integer sex,Integer isstate,Integer roleId,String currentNo){
        int _currentPageNo = 1;
        String rd ="";
        if (currentNo!=null&&!"".equals(currentNo)) {
            _currentPageNo = Integer.parseInt(currentNo);
        }
        Page page = new Page();
        page.setPageSize(4);
        page.setCurrentPageNo(_currentPageNo);
        if (roleId!=null){
             rd = roleId.toString();
        }
        Message message = userService.getUsersListByPage(username,sex,isstate,rd,page);
        return message;
    }
    @Log("注销用户")
    @RequestMapping("logout")
    public Message logout() {
        StpUtil.logout();
        return Message.success();
    }

    @Log("导出用户")
    @RequestMapping("Userexcel")
    public void Userexcel(@RequestBody SysUser sysUser , HttpServletResponse response) {
        List<String> permissionList = StpUtil.getPermissionList();
        userService.Userexcel(sysUser,response);
    }
    @Log("删除用户")
    @RequestMapping("/delUserById")
    public Message delUnitById(@RequestBody Integer[] ids) {
        Message message = userService.delUserById(ids);
        return message;
    }
    @Log("修改用户")
    @RequestMapping("/updateUser")
    public Message updateUnit(@RequestBody SysUser sysUser) {
        Message message = userService.updateUser(sysUser);
        return message;
    }
    @Log("保存用户")
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
    @Log("根据id查询用户")
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
    @Log("重置密码")
    @RequestMapping("/repass")
    public Message repass(Integer userId) {
        Message message = userService.repass(userId);
        return message;
    }
    @RequestMapping("/getAllUser")
    public Message getAllUser() {
        Message message = userService.getAllUser();
        return message;
    }

}

