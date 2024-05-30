package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.KcOutintodetialMapper;
import com.kgc.dao.KcReporteddetailMapper;
import com.kgc.entity.KcOutintodetial;
import com.kgc.entity.KcReporteddetail;
import com.kgc.entity.Message;
import com.kgc.service.KcOutintodetialService;
import com.kgc.service.KcReporteddetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KcReporteddetailServiceImpl extends ServiceImpl<KcReporteddetailMapper, KcReporteddetail> implements KcReporteddetailService {

    @Autowired
    private KcReporteddetailMapper kcReporteddetailMapper;
    @Override
    public Message insertKcReporteddetail(KcReporteddetail kcReporteddetail) {
        int count=kcReporteddetailMapper.insert(kcReporteddetail);
        if(count>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }
}
