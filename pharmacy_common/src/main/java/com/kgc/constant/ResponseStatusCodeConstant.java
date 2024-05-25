package com.kgc.constant;

public class ResponseStatusCodeConstant {
    private ResponseStatusCodeConstant() {}

    public static final int SUCCESSFUL_CODE = 200;
    public static final int FAIL_CODE = 500; // token 失效
    public static final int OAUTH_TOKEN_FAILURE = 2001; // token 失效
    public static final int OAUTH_TOKEN_MISSING = 2008; // token 缺失
    public static final int OAUTH_TOKEN_DENIED  = 2009; // token 权限不足
    public static final int PWD_ERROR = 4001; // 密码错误
    public static final int USER_LOCK = 4002; // 账号锁定
}
