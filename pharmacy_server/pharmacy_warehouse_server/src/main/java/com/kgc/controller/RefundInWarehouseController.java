package com.kgc.controller;

import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.service.RefundInWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/warehouse")
public class RefundInWarehouseController {

    @Autowired
    private RefundInWarehouseService refundInWarehouseService;

    @RequestMapping("/getRefundInWarehouseListByPage")
    @ResponseBody
    public Message getRefundInWarehouseListByPage(@RequestBody KcSalefromware kcSalefromware) {
        Message message=refundInWarehouseService.getRefundInWarehouseListByPage(kcSalefromware);
        return message;
    }


}
