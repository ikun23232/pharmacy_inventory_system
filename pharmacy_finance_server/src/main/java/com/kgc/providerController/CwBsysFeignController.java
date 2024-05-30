package com.kgc.providerController;

import com.kgc.entity.CwBsys;
import com.kgc.entity.Message;
import com.kgc.remote.CwBsysRemote;
import com.kgc.service.CwBsysService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CwBsysFeignController implements CwBsysRemote {

    @Resource
    private CwBsysService cwBsysService;

    @Override
    public Message addCwbsys(@RequestBody CwBsys cwBsys){
        return cwBsysService.addCwbsys(cwBsys);
    }


}
