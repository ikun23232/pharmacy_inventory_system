package com.kgc.providerContorller;

import com.kgc.entity.KcOutintodetial;
import com.kgc.entity.Message;
import com.kgc.remote.KcOutintodetialRemote;
import com.kgc.service.KcOutintodetialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class KcOutintodetialFeignController implements KcOutintodetialRemote {
    @Autowired
    private KcOutintodetialService kcOutintodetialService;
    @Override
    public Message addKcOutintodetial(KcOutintodetial kcOutintodetial) {
        Message message=kcOutintodetialService.addKcOutinTodetail(kcOutintodetial);
        return message;
    }
}
