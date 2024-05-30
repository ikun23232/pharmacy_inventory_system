package com.kgc.remote;

import com.kgc.entity.KcReported;
import com.kgc.entity.KcReporteddetail;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface KcReporteddetailRemote {

    @RequestMapping("/insertKcReporteddetail")
    @ResponseBody
    Message insertKcReporteddetail(@RequestBody KcReporteddetail KcReporteddetail);
}
