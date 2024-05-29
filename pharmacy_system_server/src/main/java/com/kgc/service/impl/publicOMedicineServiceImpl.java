package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kgc.dao.PublicOMedicineMapper;
import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;
import com.kgc.service.PublicOMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/10 11:51
 */
@Service
@Transactional
public class publicOMedicineServiceImpl extends ServiceImpl<PublicOMedicineMapper,OrderMedicine> implements PublicOMedicineService {
    @Autowired
    private PublicOMedicineMapper mapper;
    @Override
    public Message addMedicineOrder(OrderMedicine orderMedicine) {
        int count = mapper.insert(orderMedicine);
        if (count > 0){
            return Message.success(count);
        }
        return Message.error("添加失败");
    }

    @Override
    public Message deleteMediciOrder(Map map) {
        int i = mapper.deleteByMap(map);
        if (i > 0){
            return Message.success(i);
        }
        return Message.error("删除失败");
    }

    @Override
    public Message selectMaxYourField() {
        Integer i = mapper.selectMaxYourField();
        return Message.success(i);
    }
}
