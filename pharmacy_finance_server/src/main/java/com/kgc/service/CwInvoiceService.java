package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.CwInvoice;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;

import javax.servlet.http.HttpServletResponse;

public interface CwInvoiceService extends IService<CwInvoice> {

    Message getCwInvoice(CwInvoice cwInvoice, int pageNum, int pageSize);

    Message getCwInvoiceByCode(String code);

    Message getCategoryString();

    void cwInvoiceExcel(HttpServletResponse response);

    Message addCwInvoice(CwInvoice cwInvoice);

}
