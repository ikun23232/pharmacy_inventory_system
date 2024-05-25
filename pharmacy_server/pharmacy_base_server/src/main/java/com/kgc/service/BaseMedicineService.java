package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface BaseMedicineService extends IService<BaseMedicine> {

    public Message getBaseMedicineListByPage(BaseMedicine baseMedicine);

    public Message addBaseMedicine(BaseMedicine baseMedicine);

    public Message getBaseMedicineById(int id,int batchCode);

    public Message updateBaseMedicineById(BaseMedicine baseMedicine);

    public Message deleteBaseMedicineById(int id);

    public Message getMedicineListByCode(String code);

    public Message getBaseMedicineListByProviderId(int providerId);

    public Message getAllBaseMedicine();

    Message getMedicineListByCodeComblie(String code);
    public Message getAllBatchCodeByMedicineId(int medicineId);


}
