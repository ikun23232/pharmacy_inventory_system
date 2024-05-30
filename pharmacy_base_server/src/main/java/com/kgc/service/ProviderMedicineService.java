package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.ProviderMedicine;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/30 18:50
 */
public interface ProviderMedicineService extends IService<ProviderMedicine> {
    Message addProviderMedicine(ProviderMedicine providerMedicine);

    Message checkProviderExits(ProviderMedicine providerMedicine);
}
