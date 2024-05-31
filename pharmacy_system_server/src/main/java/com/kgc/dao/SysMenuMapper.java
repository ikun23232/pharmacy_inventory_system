package com.kgc.dao;

import com.kgc.entity.SysMenu;
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
public interface SysMenuMapper extends BaseMapper<SysMenu> {
        List<SysMenu> getMenuListByToken(Integer userId);
        List<SysMenu> getAllMenuList();

        List<Integer> getNavMenuIds(Integer userId);

        SysMenu exsitMenuName(@Param("menuname") String menuname,@Param("id") Integer id);
        SysMenu exsitMenuPerms(@Param("perms") String perms,@Param("id") Integer id);


}
