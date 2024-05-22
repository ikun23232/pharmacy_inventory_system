package com.kgc.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;


import java.util.List;

/**
 * 自定义 Sa-Token 工具类
 *
 * @author Tellsea
 * @date 2022/11/28
 */
public class SaTokenUtil {

    /**
     * 是否已经登录
     *
     * @return
     */
    public static boolean isLogin() {
        return StpUtil.isLogin();
    }

    /**
     * 获取当前用户ID
     *
     * @return
     */
    public static Long getUserId() {
        return Long.parseLong(String.valueOf(StpUtil.getLoginId()));
    }
//
//    /**
//     * 获取当前用户对象
//     *
//     * @return
//     */
//    public static SysUserDTO getUser() {
//        return JSON.parseObject(JSON.toJSONString(StpUtil.getExtra("sysUser")), SysUserDTO.class);
//    }
//
//    /**
//     * 获取当前用户名
//     *
//     * @return
//     */
//    public static String getUserName() {
//        return getUser().getUserName();
//    }
//
//    /**
//     * 获取当前用户角色列表
//     *
//     * @return
//     */
//    public static List<SysRoleDTO> getRoleList() {
//        return JSON.parseArray(JSON.toJSONString(StpUtil.getExtra("roleList")), SysRoleDTO.class);
//    }
//
//    /**
//     * 获取当前用户角色标识
//     *
//     * @return
//     */
//    public static List<String> getRoles() {
//        return StpUtil.getRoleList();
//    }
//
//    /**
//     * 获取当前用户权限列表
//     *
//     * @return
//     */
//    public static List<SysPermissionDTO> getPermissionList() {
//        return JSON.parseArray(JSON.toJSONString(StpUtil.getExtra("permissionList")), SysPermissionDTO.class);
//    }
//
//    /**
//     * 获取当前用户权限标识
//     *
//     * @return
//     */
//    public static List<String> getPermissions() {
//        return StpUtil.getPermissionList();
//    }
}
