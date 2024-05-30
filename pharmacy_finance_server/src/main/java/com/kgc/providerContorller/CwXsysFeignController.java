package com.kgc.providerContorller;

import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;
import com.kgc.remote.CwXsysRemote;
import com.kgc.service.CwXsysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CwXsysFeignController implements CwXsysRemote {

    @Autowired
    private CwXsysService cwXsysService;

    @Override
    public Message addCwXsys(CwXsys cwXsys) {
        Message message=cwXsysService.addCwXsys(cwXsys);
        return message;
    }
}
