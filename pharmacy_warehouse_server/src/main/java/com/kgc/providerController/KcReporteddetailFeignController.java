package com.kgc.providerController;

import com.kgc.entity.KcReporteddetail;
import com.kgc.entity.Message;
import com.kgc.remote.KcReporteddetailRemote;
import com.kgc.service.KcReporteddetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class KcReporteddetailFeignController implements KcReporteddetailRemote {

    @Autowired
    private KcReporteddetailService kcReporteddetailService;
    @Override
    public Message insertKcReporteddetail(KcReporteddetail kcReporteddetail) {
        Message message=kcReporteddetailService.insertKcReporteddetail(kcReporteddetail);
        return message;
    }
}
