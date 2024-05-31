package com.kgc.providerContorller;

import com.kgc.dao.SysUserMapper;
import com.kgc.entity.SysUser;
import com.kgc.remote.SysUserRemote;
import com.kgc.remote.SysUserRoleRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author daidai
 */
@RestController
public class UserRoleContorller implements SysUserRoleRemote {


    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> getAllKcAdmin() {
        List<SysUser> allKcAdmin = sysUserMapper.getAllKcAdmin();
        return allKcAdmin;
    }
}
