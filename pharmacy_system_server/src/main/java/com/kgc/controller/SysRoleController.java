package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kgc.annotation.Log;
import com.kgc.dao.SysUserMapper;
import com.kgc.entity.*;
import com.kgc.service.SysRoleMenuService;
import com.kgc.service.SysRoleService;
import com.kgc.service.SysUserRoleService;
import com.kgc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Log("根据id查询角色")
    @RequestMapping("/role/info/{id}")
    public Message info(@PathVariable("id") Integer id) {

        SysRole sysRole = sysRoleService.getById(id);

        // 获取角色相关联的菜单id
        List<SysRolemenu> roleMenus = sysRoleMenuService.list(new QueryWrapper<SysRolemenu>().eq("roleId", id));
        List<Integer> menuIds = roleMenus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());

        sysRole.setMenuIds(menuIds);
        return Message.success(sysRole);
    }

    @RequestMapping("/role/list")
    public Message list(String rolename,Integer isUse,String current) {
        int _currentPageNo = 1;

        if (current!=null&&!"".equals(current)) {
            _currentPageNo = Integer.parseInt(current);
        }
        Page page = new Page();
        page.setPageSize(2);
        page.setCurrentPageNo(_currentPageNo);
        Message message = sysRoleService.getRoleListPage(rolename,isUse,page);
        return message;
    }

    @RequestMapping("/role/AllList")
    public Message AllList() {
        Message message = sysRoleService.getAllList();
        return message;
    }


    @Log("添加角色")
    @RequestMapping("/role/save")
    public Message save(@Validated @RequestBody SysRole sysRole) {
//        SysUser sysUser = sysUserMapper.existUser(token,null);
        SysUser sysUser = (SysUser) StpUtil.getSession().get("user");
        sysRole.setCreateby(sysUser.getUserid());
        sysRole.setCreatetime(new Date());
        sysRoleService.save(sysRole);
        return Message.success(sysRole);
    }

    @Log("修改角色")
    @RequestMapping("/role/update")
    public Message update(@Validated @RequestBody SysRole sysRole) {

        sysRole.setUpdatetime(new Date());

        sysRoleService.updateById(sysRole);

        // 更新缓存
        sysUserService.clearUserAuthorityInfoByRoleId(sysRole.getRoleid());

        return Message.success(sysRole);
    }
    @Log("根据id删除角色")
    @RequestMapping("/role/delete")
    public Message info(@RequestBody Integer[] ids) {

        sysRoleService.removeByIds(Arrays.asList(ids));

        // 删除中间表
        sysUserRoleService.remove(new QueryWrapper<SysUserrole>().in("roleId", ids));
        sysRoleMenuService.remove(new QueryWrapper<SysRolemenu>().in("roleId", ids));

        // 缓存同步删除
        Arrays.stream(ids).forEach(id -> {
            // 更新缓存
            sysUserService.clearUserAuthorityInfoByRoleId(id);
        });

        return Message.success("操作成功");
    }
    @PostMapping("/role/perm/{roleId}")
    public Message info(@PathVariable("roleId") Integer roleId, @RequestBody Integer[] menuIds) {

        List<SysRolemenu> sysRoleMenus = new ArrayList<>();

        Arrays.stream(menuIds).forEach(menuId -> {
            SysRolemenu roleMenu = new SysRolemenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);

            sysRoleMenus.add(roleMenu);
        });

        // 先删除原来的记录，再保存新的
        sysRoleMenuService.remove(new QueryWrapper<SysRolemenu>().eq("roleId", roleId));
        sysRoleMenuService.saveBatch(sysRoleMenus);

        // 删除缓存
        sysUserService.clearUserAuthorityInfoByRoleId(roleId);

        return Message.success(menuIds);
    }

}

