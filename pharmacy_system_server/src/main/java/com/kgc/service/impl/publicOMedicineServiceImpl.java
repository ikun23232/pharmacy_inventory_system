package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.PublicOMedicineMapper;
import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;
import com.kgc.service.PublicOMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/10 11:51
 */
@Service
public class publicOMedicineServiceImpl extends ServiceImpl<PublicOMedicineMapper,OrderMedicine> implements PublicOMedicineService {
    @Autowired
    private PublicOMedicineMapper mapper;
    @Override
    public Message addMedicineOrder(OrderMedicine orderMedicine) {
        int count = mapper.insert(orderMedicine);
        if (count > 0){
            return Message.success();
        }
        return Message.error("添加失败");
    }
}