package com.kgc.constant;
/**
 * 响应 Code 常量定义类
 */
public class ResponseCodeConstant {
    private ResponseCodeConstant() {}

    public static final int REQUEST_SUCCESS = 1; // 请求成功
    public static final int REQUEST_FAILED  = 0; // 请求失败
    public static final int SYSTEM_ERROR    = -1; // 系统错误
    public static final int OAUTH_TOKEN_FAILURE = 2001; // token 无效
    public static final int OAUTH_TOKEN_MISSING = 2008; // token 缺失
    public static final int OAUTH_TOKEN_DENIED  = 2009; // token 权限不足
    public static final int PWD_ERROR = 4001; // 密码错误
    public static final int USER_LOCK = 4002; // 账号锁定
}
