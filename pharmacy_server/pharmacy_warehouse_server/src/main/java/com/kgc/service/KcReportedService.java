package com.kgc.service;

import com.kgc.entity.BaseStorehouse;
import com.kgc.entity.KcReported;
import com.kgc.entity.KcReportedtype;
import com.kgc.entity.Message;

import java.util.List;

public interface KcReportedService {
    //查询库存报损列表
    Message getKcReportedList(KcReported kcReported,int pageNum,int pageSize);
    //根据code查询code
    Message getCodeByCode(String code);
    //查询仓库列表
    Message getStorehouseList();
    //查询报损类型
    Message getReportedType();
    //添加库存报损
    Message addKcReported(KcReported kcReported);
}
