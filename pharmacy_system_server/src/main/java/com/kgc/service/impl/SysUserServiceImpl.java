package com.kgc.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SysMenuMapper;
import com.kgc.dao.SysRoleMapper;
import com.kgc.dao.SysUserMapper;
import com.kgc.entity.*;
import com.kgc.service.SysMenuService;
import com.kgc.service.SysRoleService;
import com.kgc.service.SysUserRoleService;
import com.kgc.service.SysUserService;
import com.kgc.utils.ExeclUtil;
import com.kgc.utils.Md5Util;
import com.kgc.utils.RedisUtil;
import com.kgc.vo.PandianDetailVo;
import com.kgc.vo.PandianMedicineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysUserRoleService sysUserRoleService;


    @Override
    public Message existUser(String userName,Integer id) {
        SysUser sysUser = sysUserMapper.existUser(userName,id);
        if (sysUser != null) {
            return Message.error("202","用户不为空",sysUser);
        }
        return Message.success(sysUser);
    }

    @Override
    public Message existLogin(SysUser user) {
        String md5String = Md5Util.getMD5String(user.getPassword());
        user.setPassword(md5String);
        String loginId = StpUtil.getLoginId().toString();
        user.setUsername(loginId);
        SysUser sysUser = sysUserMapper.login(user);
        if (sysUser == null) {
            return Message.error("原密码输入错误");
        }
        return Message.success(sysUser);
    }

    @Override
    public Message login(SysUser user) {
        String md5String = Md5Util.getMD5String(user.getPassword());
        user.setPassword(md5String);
        SysUser login = sysUserMapper.existUser(user.getUsername(),null);
        if (login == null) {
            return Message.error("登录失败,用户不存在");
        }

        if (!login.getPassword().equals(user.getPassword())) {
            return Message.error("密码错误");
        }
        try {
            StpUtil.checkDisable(login.getUsername());
        } catch (Exception e) {
            return Message.error("账号已被禁止访问服务:还剩 " +StpUtil.getDisableTime(10001) +"秒解封");
        }
        StpUtil.login(login.getUsername());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String token = tokenInfo.getTokenValue();
        if (token == null) {
            return Message.error("token为空");
        }
        redisUtil.setKey(token, token, 30);
        StpUtil.getSession().set("user",login);
        return Message.success(tokenInfo);
    }


    @Override
    public Message getUsersListByPage(String username, Integer sex, Integer isstate, String roleId, Page page) {
        long count = PageHelper.count(() -> sysUserMapper.getUsersListByPage(username, sex, isstate, roleId));
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<SysUser> usersListByPage = sysUserMapper.getUsersListByPage(username, sex, isstate, roleId);

        List<SysUser> list = new ArrayList<>();
        for (SysUser user : usersListByPage) {
            List<SysRole> roleList = sysRoleMapper.getRoleList(user.getUserid());
            user.setSysRoles(roleList);
            list.add(user);
        }

        // 获取实际总记录数
        PageInfo pageInfo = new PageInfo(usersListByPage);

        if (usersListByPage != null) {

            pageInfo.setTotal(count);

            return Message.success(pageInfo);
        }

        return Message.error("数据为空");
    }

    @Override
    public Message getAllUser() {
        List<SysUser> allUser = sysUserMapper.getAllUser();
        return Message.success(allUser);
    }

    @Override
    public Message delUserById( Integer[] ids) {
        boolean flag = this.removeByIds(Arrays.asList(ids));

        if (flag ) {
            boolean flag1 = sysUserRoleService.remove(new QueryWrapper<SysUserrole>().in("userId", ids));
            return Message.success(flag1);
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateUser(SysUser sysUser) {
        String tokenValue = StpUtil.getTokenValue();
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(loginIdByToken,null);
        sysUser.setUpdateby(loginUser.getUserid());
        sysUser.setUpdatedate(new Date());
        boolean flag = this.updateById(sysUser);
        if (sysUser.getIsstate()==0){
            StpUtil.kickout(sysUser.getUsername());
            StpUtil.disable(sysUser.getUsername(), 86400);
        }
        if (sysUser.getIsstate()==1){
            StpUtil.untieDisable(sysUser.getUsername());

        }
        if (flag) {
            return Message.success(flag);
        }
        return Message.error("修改失败");
    }

    @Override
    public Message selectUser() {
        String tokenValue = StpUtil.getTokenValue();
        if (tokenValue==null){
            return Message.error("token为空");
        }
        String username = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(username,null);
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
    public Message rolePerm(Integer userId, Integer[] roleIds) {
        List<SysUserrole> userRoles = new ArrayList<>();

        Arrays.stream(roleIds).forEach(r -> {
            SysUserrole sysUserRole = new SysUserrole();
            sysUserRole.setRoleId(r);
            sysUserRole.setUserId(userId);
            userRoles.add(sysUserRole);
        });
        sysUserRoleService.remove(new QueryWrapper<SysUserrole>().eq("userId", userId));
        sysUserRoleService.saveBatch(userRoles);
        // 删除缓存
        SysUser sysUser = this.getById(userId);
        this.clearUserAuthorityInfo(sysUser.getUsername());

        return Message.success("更改角色成功");
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

    @Override
    public Message repass(Integer userId) {
        SysUser sysUser = this.getById(userId);

        sysUser.setPassword(Md5Util.getMD5String("123456"));
        sysUser.setUpdatedate(new Date());

        boolean flag = this.updateById(sysUser);
        return Message.success(flag);
    }

    @Override
    public void Userexcel(SysUser sysUser, HttpServletResponse response) {
        List<SysUser> allUser = sysUserMapper.getAllUser();
        List<SysUser> temp = new ArrayList<>();
        for (SysUser user : allUser) {
            List<SysRole> allKcInventoryDetailById = sysRoleMapper.getRoleList(sysUser.getUserid());
            sysUser.setSysRoles(allKcInventoryDetailById);
            temp.add(user);
        }
        try {
            ExeclUtil.write(temp, SysUser.class,response,"用户信息");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message updatePass(String password) {
        String md5String = Md5Util.getMD5String(password);
        SysUser user = (SysUser) StpUtil.getSession().get("user");
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("userId", user.getUserid());
        updateWrapper.set("password",md5String);
        baseMapper.update(user, updateWrapper);
        return Message.success("修改成功");
    }

}
