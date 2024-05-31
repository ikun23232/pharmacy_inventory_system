package com.kgc.providerController;

import com.kgc.entity.SysLogManage;
import com.kgc.remote.SysLogRemote;
import com.kgc.service.SysLogManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
