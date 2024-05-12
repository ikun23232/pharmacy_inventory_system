package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysRole;
import com.kgc.dao.SysRoleMapper;
import com.kgc.entity.SysUser;
import com.kgc.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
@Autowired
private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> listRolesByUserId(Integer userId) {

        List<SysRole> roleList = sysRoleMapper.getRoleList(userId);

        return roleList;
    }

    @Override
    public Message getRoleListPage(String rolename, Integer isUse, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<SysRole> roleListPage = sysRoleMapper.getRoleListPage(rolename, isUse);
        PageInfo pageInfo = new PageInfo(roleListPage);
        if (roleListPage != null){
            return Message.success(pageInfo);
        }
        return Message.error("数据为空");
    }
}
