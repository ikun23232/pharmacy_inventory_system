package com.kgc.service;

import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;
import com.kgc.vo.KcMedicineBSVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KcMedicineService {
    //库存药品列表
    Message getKcMedicine(KcMedicine kcMedicine, int pageNum, int pageSize);
    //根据报损单号查询库存药品
    Message getKcMedicineByReportedCode(String reportedCode);
    //根据报损单号查询库存药品
    List<KcMedicine> getKcMedicineByReportedCodeTo(String reportedCode);
    //修改库存药品
    Message updateQuantityById(KcMedicine kcMedicine);
    //根据报损单号查询库存药品VO
    List<KcMedicineBSVO> getKcMedicineVOByReportedCode(String reportedCode);

    //根据仓库id和药品id查询库存药品
    List<KcMedicine> getMedicineByStorehouseIdMedicineId(KcMedicine kcMedicine);

}
