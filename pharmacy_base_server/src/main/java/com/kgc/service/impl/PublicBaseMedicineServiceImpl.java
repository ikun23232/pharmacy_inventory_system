package com.kgc.service.impl;

import com.kgc.dao.BaseMedicineCategoryMapper;
import com.kgc.dao.BaseMedicineMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.service.PublicBaseMedicineService;
import com.kgc.vo.MedicineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 15:22
 */
@Service
public class PublicBaseMedicineServiceImpl implements PublicBaseMedicineService {

    @Autowired
    private BaseMedicineMapper baseMedicineMapper;

    @Override
    public Message getMedicineListByCode(String code) {
        List<BaseMedicine> medicineListByCode = baseMedicineMapper.getMedicineListByCode(code);
        return Message.success(medicineListByCode);
    }

    @Override
    public Message getMedicineVOListByCode(String code) {
        List<MedicineVO> medicineListByCode = baseMedicineMapper.getMedicineVOListByCode(code);
        return Message.success(medicineListByCode);
    }

    @Override
    public List<MedicineVO> getMedicineVOListByCodes(String code) {
        List<MedicineVO> medicineListByCode = baseMedicineMapper.getMedicineVOListByCode(code);
        return medicineListByCode;
    }
}
