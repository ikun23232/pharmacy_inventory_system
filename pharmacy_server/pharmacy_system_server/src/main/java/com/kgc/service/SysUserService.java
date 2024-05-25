package com.kgc.service;

import com.kgc.entity.BaseUnit;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

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
    public Message existUser(String userName);
    public Message login(SysUser user);
    public Message getUsersListByPage(String username,Integer sex,Integer isstate, Page page);

    public Message delUserById(int userid);
    public Message updateUser(SysUser sysUser);
    public Message selectUser();
    public String getUserAuthorityInfo(Integer userId);
    public void clearUserAuthorityInfo(String username);

    void clearUserAuthorityInfoByRoleId(Integer roleId);

    void clearUserAuthorityInfoByMenuId(Integer menuId);

    public Message getAllUser();

}
