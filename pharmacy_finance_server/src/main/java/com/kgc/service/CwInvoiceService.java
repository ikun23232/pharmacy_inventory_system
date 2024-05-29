package com.kgc.service;

import com.kgc.entity.CwInvoice;
import com.kgc.entity.Message;

import javax.servlet.http.HttpServletResponse;

public interface CwInvoiceService {

    Message getCwInvoice(CwInvoice cwInvoice, int pageNum, int pageSize);

    Message getCwInvoiceByCode(String code);

    Message getCategoryString();

    void cwInvoiceExcel(HttpServletResponse response);

}
