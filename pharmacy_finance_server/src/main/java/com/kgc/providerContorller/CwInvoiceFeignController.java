package com.kgc.providerContorller;

import com.kgc.entity.CwInvoice;
import com.kgc.entity.Message;
import com.kgc.remote.CwInvoiceRemote;
import com.kgc.service.CwInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CwInvoiceFeignController implements CwInvoiceRemote {

    @Autowired
    private CwInvoiceService cwInvoiceService;
    @Override
    public Message addCwInvoice(CwInvoice cwInvoice) {
        Message message=cwInvoiceService.addCwInvoice(cwInvoice);
        return message;
    }
}
