package com.kgc.service;

import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;

import java.util.List;

public interface KcMedicineService {
    //库存药品列表
    Message getKcMedicine(KcMedicine kcMedicine, int pageNum, int pageSize);
    //根据报损单号查询库存药品
    Message getKcMedicineByReportedCode(int storehouseId,String reportedCode);
    //修改库存药品
    Message updateQuantityById(KcMedicine kcMedicine);

}