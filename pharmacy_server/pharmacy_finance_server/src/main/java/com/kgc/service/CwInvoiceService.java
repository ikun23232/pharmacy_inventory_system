package com.kgc.service;

import com.kgc.entity.CwInvoice;
import com.kgc.entity.Message;

public interface CwInvoiceService {

    Message getCwInvoice(CwInvoice cwInvoice, int pageNum, int pageSize);

    Message getCategoryString();

}
