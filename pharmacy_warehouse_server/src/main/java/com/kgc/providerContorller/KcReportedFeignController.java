package com.kgc.providerContorller;

import com.kgc.entity.KcReported;
import com.kgc.entity.Message;
import com.kgc.remote.KcReportedRemote;
import com.kgc.service.KcReportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class KcReportedFeignController implements KcReportedRemote {

    @Autowired
    private KcReportedService kcReportedService;
    @Override
    public Message insertKcReported(KcReported kcReported) {
       Message message=kcReportedService.insertKcReported(kcReported);
       return message;
    }
}
