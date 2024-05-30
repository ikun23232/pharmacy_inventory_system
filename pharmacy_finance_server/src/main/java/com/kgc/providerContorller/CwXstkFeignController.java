package com.kgc.providerContorller;

import com.kgc.entity.CwXstk;
import com.kgc.entity.Message;
import com.kgc.remote.CwXstkRemote;
import com.kgc.service.CwXstkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CwXstkFeignController implements CwXstkRemote {

    @Autowired
    private CwXstkService cwXstkService;
    @Override
    public Message addCwXstk(CwXstk cwXstk) {
        Message message=cwXstkService.addCwXstk(cwXstk);
        return message;
    }
}
