package com.kgc.providerContorller;

import cn.dev33.satoken.stp.StpInterface;
import com.kgc.entity.SysLogManage;
import com.kgc.remote.SysLogRemote;
import com.kgc.remote.SysUserRemote;
import com.kgc.service.SysLogManageService;
import com.kgc.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author daidai
 */
@RestController
public class SysLogController implements SysLogRemote {
    @Autowired
    private  SysLogManageService sysOperationLogService;

    @Override
    public boolean addLogManagement(SysLogManage sysLogManage) {
        boolean save = sysOperationLogService.save(sysLogManage);
        return save;
    }
}
