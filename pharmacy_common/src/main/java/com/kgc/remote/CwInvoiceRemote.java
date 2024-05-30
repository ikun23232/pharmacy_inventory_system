package com.kgc.remote;

import com.kgc.entity.CwInvoice;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface CwInvoiceRemote {

    @RequestMapping("/addCwInvoice")
    @ResponseBody
    Message addCwInvoice(@RequestBody CwInvoice cwInvoice);
}
