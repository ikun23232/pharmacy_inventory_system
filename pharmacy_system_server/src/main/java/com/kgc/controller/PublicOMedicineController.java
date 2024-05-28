package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;
import com.kgc.remote.PublicOMedicineRemote;
import com.kgc.service.PublicOMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 14:48
 */

@RestController
public class PublicOMedicineController implements PublicOMedicineRemote {

    @Autowired
    private PublicOMedicineService publicOMedicineService;
    public Message addMedicineOrder(OrderMedicine orderMedicine){
        Message message = publicOMedicineService.addMedicineOrder(orderMedicine);
        return message;
    }

    public Message deleteMediciOrder(Map map){
        Message message = publicOMedicineService.deleteMediciOrder(map);
        return message;
    }
}
