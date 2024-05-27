package com.kgc.controller;


import com.kgc.entity.Message;
import com.kgc.service.RefundTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sale")
public class RefundTypeController {
    @Autowired
    private RefundTypeService refundTypeService;

    @RequestMapping("/getAllRefundTypeList")
    @ResponseBody
    public Message getAllRefundTypeList() {
        Message message=refundTypeService.getRefundTypeList();
        return message;
    }
}
