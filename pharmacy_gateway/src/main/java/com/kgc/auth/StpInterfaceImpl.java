package com.kgc.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色权限集合
 *
 * @Author: 魏小可
 * @Date: 2024-05-07-19:16
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        // 返回此 loginId 拥有的权限列表
        List<String> stringList = new ArrayList<>();
        stringList.add("system");
        return stringList;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        // 返回此 loginId 拥有的角色列表
        List<String> stringList = new ArrayList<>();
        stringList.add("admin");
        return stringList;
    }
}
