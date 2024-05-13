package com.kgc.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.kgc.entity.SysUser;

/**
 * @author daidai
 */
public class GetUser {
    public static String getUser(){
        String tokenValue = StpUtil.getTokenValue();
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        return loginIdByToken;
    }
}
