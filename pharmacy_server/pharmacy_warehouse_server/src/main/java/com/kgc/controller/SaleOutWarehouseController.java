package com.kgc.controller;

import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.service.SaleOutWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class SaleOutWarehouseController {
    @Autowired
    private SaleOutWarehouseService saleOutWarehouseService;

    @RequestMapping("/getSaleOutWarehouseListByPage")
    @ResponseBody
    public Message getSaleOutWarehouseListByPage(@RequestBody KcSalefromware kcSalefromware) {
        Message message=saleOutWarehouseService.getSaleOutWarehouseListByPage(kcSalefromware);
        return message;
    }
    @RequestMapping("/deleteSaleOutWarehouseOrder")
    @ResponseBody
    public Message deleteSaleOutWarehouseOrder(String code){
        Message message=saleOutWarehouseService.deleteSaleOutWarehouseOrder(code);
        return message;
    }



}
