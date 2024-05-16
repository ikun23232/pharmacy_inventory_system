package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.RefundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sale")
public class RefundOrderController {

    @Autowired
    private RefundOrderService refundOrderService;

    @RequestMapping("/getRefundOrderListByPage")
    @ResponseBody
    public Message getRefundOrderListByPage(@RequestBody XsOrder xsOrder) {
        Message message=refundOrderService.getRefundOrderListByPage(xsOrder);
        return message;
    }
}
