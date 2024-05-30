package com.kgc.service.Impl;

import cn.dev33.satoken.stp.StpInterface;
import com.kgc.feign.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserAuth userAuth;

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> authRole = userAuth.getAuthRole(loginId, loginType);
        return authRole;
    }

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 从session中获取
        List<String> authpermission = userAuth.getAuthpermission(loginId, loginType);
        return authpermission;

    }
}

