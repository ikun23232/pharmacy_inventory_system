package com.kgc.service;

import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;

import java.util.List;

public interface KcMedicineService {

    Message getKcMedicine(KcMedicine kcMedicine, int pageNum, int pageSize);

}
