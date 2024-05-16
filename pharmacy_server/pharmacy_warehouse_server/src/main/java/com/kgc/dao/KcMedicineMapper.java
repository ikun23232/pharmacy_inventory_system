package com.kgc.dao;

import com.kgc.entity.KcMedicine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KcMedicineMapper {

    //查询库存药品
    List<KcMedicine> getKcMedicine(KcMedicine kcMedicine);

    List<KcMedicine> getKcMedicineByReportedCode(@Param("storehouseId") int storehouseId,@Param("reportedCode") String reportedCode);

    int updateQuantityById(KcMedicine kcMedicine);
}
