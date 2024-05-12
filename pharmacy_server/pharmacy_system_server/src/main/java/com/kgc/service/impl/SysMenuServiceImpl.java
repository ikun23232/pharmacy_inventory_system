package com.kgc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.kgc.dao.SysUserMapper;
import com.kgc.dto.SysMenuDto;
import com.kgc.entity.BaseMedicineCategory;
import com.kgc.entity.Message;
import com.kgc.entity.SysMenu;
import com.kgc.dao.SysMenuMapper;
import com.kgc.entity.SysUser;
import com.kgc.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public Message getMenuListByToken(Integer userId) {
        // 1.查询出所有的分类
        List<SysMenu> entities = sysMenuMapper.getMenuListByToken(userId);
        // 2.组装成父子的树形结构
        List<SysMenu> level1Menus = new ArrayList<>();
        // 找到所有的一级分类
        for (SysMenu entity : entities) {
            if (entity.getParentid() == 0) {
                level1Menus.add(entity);
            }
        }
        for (SysMenu level1Menu : level1Menus) {
            level1Menu.setChildren(getChildrens(level1Menu, entities));
        }
        //排序
        return Message.success(level1Menus);
    }

    @Override
    public Message getAllMenuList() {
        List<SysMenu> entities = sysMenuMapper.getAllMenuList();
        // 2.组装成父子的树形结构
        List<SysMenu> level1Menus = new ArrayList<>();
        // 找到所有的一级分类
        for (SysMenu entity : entities) {
            if (entity.getParentid() == 0) {
                level1Menus.add(entity);
            }
        }
        for (SysMenu level1Menu : level1Menus) {
            level1Menu.setChildren(getChildrens(level1Menu, entities));
        }
        //排序
        return Message.success(level1Menus);
    }

    @Override
    public Message getAuth() {
        String tokenValue = StpUtil.getTokenValue();
        String loginIdByToken = (String)StpUtil.getLoginIdByToken(tokenValue);
        SysUser loginUser = sysUserMapper.existUser(loginIdByToken);
        String authorityInfo = sysUserService.getUserAuthorityInfo(loginUser.getUserid());// ROLE_admin,ROLE_normal,sys:user:list,....
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");
        Message menuListByToken = this.getMenuListByToken(loginUser.getUserid());
        Object data = menuListByToken.getData();
        List<SysMenu> menuList = (List<SysMenu>) data;
        Map map=new HashMap<>();
        map.put("authoritys",authorityInfoArray);
        map.put("nav", menuList);
        return Message.success(map);


    }

    private List<SysMenu> getChildrens(SysMenu root, List<SysMenu> all) {
        List<SysMenu> children = new ArrayList<>();
        for (SysMenu a : all) {
            if (a.getParentid().equals(root.getId())) {
                a.setChildren(getChildrens(a, all));
                children.add(a);
            }
        }
        return children;
    }



}
