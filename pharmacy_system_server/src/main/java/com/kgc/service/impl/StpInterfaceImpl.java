package com.kgc.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.kgc.dao.SysMenuMapper;
import com.kgc.dao.SysRoleMapper;
import com.kgc.dao.SysUserMapper;
import com.kgc.entity.SysMenu;
import com.kgc.entity.SysRole;
import com.kgc.entity.SysUser;
import com.kgc.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserMapper sysUserMapper;


    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SysUser loginUser = sysUserMapper.existUser(loginId.toString(), null);
        List<String> list = new ArrayList<>();
        List<SysRole> roles = sysRoleMapper.getRoleList(loginUser.getUserid());
        if (roles.isEmpty()) {
            return null;
        }
        for (SysRole role : roles) {
            list.add(role.getCode());
        }

        return list;
    }

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 从session中获取
        SysUser loginUser = sysUserMapper.existUser(loginId.toString(), null);
        List<Integer> menuIds = sysMenuMapper.getNavMenuIds(loginUser.getUserid());
        List<String> list = new ArrayList<>();
        // 超级管理员
//        if (StpUtil.hasRole("administrator")) {
//            List<String> list1 = new ArrayList<>();
//            list1.add("*");
//            return list1;
//        }
        if (menuIds.size() > 0) {
            List<SysMenu> menus = sysMenuService.listByIds(menuIds);
            if (menus.isEmpty()) {
                return null;
            }
            for (SysMenu menu : menus) {
                list.add(menu.getPerms());
            }

        }
        return list;
    }
}

