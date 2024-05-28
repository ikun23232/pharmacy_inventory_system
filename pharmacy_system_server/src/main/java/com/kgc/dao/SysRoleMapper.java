package com.kgc.dao;

import com.kgc.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getRoleList(Integer userId);
    List<SysRole> getRoleListPage(@Param("rolename") String rolename,@Param("isUse") Integer isUse);

}
