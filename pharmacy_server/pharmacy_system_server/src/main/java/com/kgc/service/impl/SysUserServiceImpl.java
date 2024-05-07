package com.kgc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.SysUserMapper;
import com.kgc.entity.Message;
import com.kgc.entity.SysUser;
import com.kgc.service.SysUserService;
import com.kgc.util.JwtUtil;
import com.kgc.util.Md5Util;
import com.kgc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Message existUser(String userName) {
        return null;
    }

    @Override
    public Message login(SysUser user) {
        String md5String = Md5Util.getMD5String(user.getPassword());
        user.setPassword(md5String);
        SysUser login = sysUserMapper.login(user);
        if (login == null) {
            return Message.error("登录失败,用户不存在");
        }
        if (!login.getPassword().equals(user.getPassword())) {
            return Message.error("密码错误");
        }
        StpUtil.login(login.getUsername());
        String token = StpUtil.getTokenInfo().getTokenValue();
        if (token == null) {
            return Message.error("token为空");
        }
        redisUtil.setKey(token, token, 30);
        Map<String, Object> info = new HashMap<>();
        info.put("user", login);
        //存放sessionId, 即 token
        info.put("sessionId", token);
        return Message.success(info);
    }
}
