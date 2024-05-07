package com.kgc.dao;

import com.kgc.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
     SysUser existUser(String userName);
     SysUser login(SysUser user);

}
