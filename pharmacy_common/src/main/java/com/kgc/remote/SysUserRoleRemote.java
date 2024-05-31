package com.kgc.remote;

import com.kgc.entity.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author daidai
 */
public interface SysUserRoleRemote {

    @RequestMapping("/getAllKcAdmin")
    @ResponseBody
    public List<SysUser> getAllKcAdmin();
}
