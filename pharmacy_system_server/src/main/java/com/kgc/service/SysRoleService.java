package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface SysRoleService extends IService<SysRole> {

    public List<SysRole> listRolesByUserId(Integer userId);
    public Message getRoleListPage(String rolename,Integer isUse,Page page);

    public Message getAllList();


}
