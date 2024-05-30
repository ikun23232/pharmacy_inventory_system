package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.vo.MedicineVO;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 15:20
 */
public interface PublicBaseMedicineService {
    public Message getMedicineListByCode(String code);

    Message getMedicineVOListByCode(String code);

    List<MedicineVO> getMedicineVOListByCodes(String code);
}
