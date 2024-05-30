package com.kgc.providerContorller;

import cn.dev33.satoken.stp.StpInterface;
import com.kgc.remote.SysUserRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author daidai
 */
@RestController
public class UserAuthController implements SysUserRemote {
    @Autowired
    private StpInterface stpInterface;
    @Override
    public List<String> getAuthRole(Object loginId, String loginType) {
        List<String> roleList = stpInterface.getRoleList(loginId, loginType);
        return roleList;
    }

    @Override
    public List<String> getAuthpermission(Object loginId, String loginType) {
        List<String> permissionList = stpInterface.getPermissionList(loginId, loginType);
        return permissionList;
    }
}
