package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.KcOutintodetial;
import com.kgc.entity.KcReporteddetail;
import com.kgc.entity.Message;

public interface KcReporteddetailService extends IService<KcReporteddetail> {

    Message insertKcReporteddetail(KcReporteddetail kcReporteddetail);
}
