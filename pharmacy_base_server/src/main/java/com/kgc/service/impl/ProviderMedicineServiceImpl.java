package com.kgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.ProviderMedicineMapper;
import com.kgc.entity.Message;
import com.kgc.entity.ProviderMedicine;
import com.kgc.service.ProviderMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/30 18:52
 */
@Service
public class ProviderMedicineServiceImpl extends ServiceImpl<ProviderMedicineMapper, ProviderMedicine> implements ProviderMedicineService {
    @Autowired
    private ProviderMedicineMapper providerMedicineMapper;
    @Override
    public Message addProviderMedicine(ProviderMedicine providerMedicine) {
        int insert = providerMedicineMapper.insert(providerMedicine);
        if (insert > 0){
            return Message.success();
        }
        return Message.error();
    }

    @Override
    public Message checkProviderExits(ProviderMedicine providerMedicine) {
        QueryWrapper<ProviderMedicine> wrapper = new QueryWrapper<>();
        wrapper.eq("providerId",providerMedicine.getProviderId()).
                eq("medicineId",providerMedicine.getMedicineId());
        ProviderMedicine providerMedicine1 = providerMedicineMapper.selectOne(wrapper);
        if (providerMedicine1 != null){
            return Message.success(providerMedicine1);
        }
        return Message.error("没有找到");
    }
}
