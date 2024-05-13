package com.kgc.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SysMenuMapper;
import com.kgc.dao.SysRoleMapper;
import com.kgc.dao.SysUserMapper;
import com.kgc.entity.*;
import com.kgc.service.SysMenuService;
import com.kgc.service.SysRoleService;
import com.kgc.service.SysUserService;
import com.kgc.utils.Md5Util;
import com.kgc.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private SysMenuMapper sysMenuMapper;
    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    SysRoleMapper sysRoleMapper;


    @Override
    public Message existUser(String userName) {
        SysUser sysUser = sysUserMapper.existUser(userName);
        if (sysUser != null) {
            return Message.error("用户不为空",sysUser);
        }
        return Message.success(sysUser);
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
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String token = tokenInfo.getTokenValue();
        if (token == null) {
            return Message.error("token为空");
        }
        redisUtil.setKey(token, token, 30);
        return Message.success(tokenInfo);
    }


    @Override
    public Message getUsersListByPage(String username,Integer sex,Integer isstate,Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<SysUser> usersListByPage = sysUserMapper.getUsersListByPage(username,sex,isstate);
        PageInfo pageInfo = new PageInfo(usersListByPage);
        if (usersListByPage != null){
            return Message.success(pageInfo);
        }
        return Message.error("数据为空");
    }

    @Override
    public Message delUserById(int userid) {
        int updateRow = sysUserMapper.deleteById(userid);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateUser(SysUser sysUser) {
        String tokenValue = StpUtil.getTokenValue();
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(loginIdByToken);
        sysUser.setUpdateby(loginUser.getUserid());
        sysUser.setUpdatedate(new Date());
        int updateRow = sysUserMapper.updateById(sysUser);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("修改失败");
    }

    @Override
    public Message selectUser() {
        String tokenValue = StpUtil.getTokenValue();
        if (tokenValue==null){
            return Message.error("token为空");
        }
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(loginIdByToken);
        return Message.success(loginUser);
    }

    @Override
    public String getUserAuthorityInfo(Integer userId) {


        //  ROLE_admin,ROLE_normal,sys:user:list,....
        String authority = "";

        List<SysRole> roles = sysRoleMapper.getRoleList(userId);

        if (roles.size() > 0) {
                String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
                authority = roleCodes.concat(",");
            }

            // 获取菜单操作编码
            List<Integer> menuIds = sysMenuMapper.getNavMenuIds(userId);
            if (menuIds.size() > 0) {

                List<SysMenu> menus = sysMenuService.listByIds(menuIds);
                String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));

                authority = authority.concat(menuPerms);
            }

//            redisUtil.set("GrantedAuthority:" + sysUser.getUsername(), authority, 60 * 60);

        return authority;
    }

    @Override
    public void clearUserAuthorityInfo(String username) {
        redisUtil.del("GrantedAuthority:" + username);
    }

    @Override
    public void clearUserAuthorityInfoByRoleId(Integer roleId) {

        List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>()
                .inSql("userId", "select userId from sys_userrole where roleId = " + roleId));

        sysUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getUsername());
        });

    }

    @Override
    public void clearUserAuthorityInfoByMenuId(Integer menuId) {
        List<SysUser> sysUsers = sysUserMapper.listByMenuId(menuId);

        sysUsers.forEach(u -> {
            this.clearUserAuthorityInfo(u.getUsername());
        });
    }

}
