package com.kgc.controller;

import com.kgc.entity.CwInvoice;
import com.kgc.entity.Message;
import com.kgc.service.CwInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class CwInvoiceController {

    @Autowired
    private CwInvoiceService cwInvoiceService;

    @RequestMapping("/getCwInvoice")
    public Message getCwInvoice(@RequestBody CwInvoice cwInvoice, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){
        return cwInvoiceService.getCwInvoice(cwInvoice,pageNum,pageSize);
    }

    @RequestMapping("/getCategoryString")
    public Message getCategoryString(){
        return cwInvoiceService.getCategoryString();
    }

}
