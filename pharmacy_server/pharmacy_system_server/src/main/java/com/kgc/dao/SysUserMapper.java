package com.kgc.dao;

import com.kgc.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
     SysUser existUser(String username);
     SysUser selectUser();

     SysUser login(SysUser user);
     List<SysUser> getUsersListByPage(@Param("username") String username, @Param("sex")Integer sex, @Param("isstate")Integer isstate);
     List<SysUser> listByMenuId(Integer menuId);

}
