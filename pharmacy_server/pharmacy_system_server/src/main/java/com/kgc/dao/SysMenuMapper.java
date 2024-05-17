package com.kgc.dao;

import com.kgc.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
