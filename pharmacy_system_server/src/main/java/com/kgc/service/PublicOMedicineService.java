package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;

import java.util.Map;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/10 11:50
 */
public interface PublicOMedicineService extends IService<OrderMedicine>  {
    public Message addMedicineOrder(OrderMedicine orderMedicine);

    Message deleteMediciOrder(Map map);
}
