package com.kgc.dao;

import com.kgc.entity.KcMedicine;

import java.util.List;

public interface KcMedicineMapper {

    //查询库存药品
    List<KcMedicine> getKcMedicine(KcMedicine kcMedicine);
}
