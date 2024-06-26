package com.kgc.service;

import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.vo.KcInventoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface SysUserService extends IService<SysUser> {
    public Message existUser(String userName,Integer id);
    public Message login(SysUser user);
    public Message existLogin(SysUser user);
    public Message getUsersListByPage(String username,Integer sex,Integer isstate,String roleId, Page page);
    public Message getAllUser();
    public Message delUserById(Integer[] ids);
    public Message updateUser(SysUser sysUser);
    public Message selectUser();
    public String getUserAuthorityInfo(Integer userId);
    public Message rolePerm(Integer userId, Integer[] roleIds);
    public void clearUserAuthorityInfo(String username);

    void clearUserAuthorityInfoByRoleId(Integer roleId);

    void clearUserAuthorityInfoByMenuId(Integer menuId);
    public Message repass( Integer userId);
    public void Userexcel(SysUser sysUser, HttpServletResponse response);

    public Message updatePass(String password);


}
