package com.kgc.dao;

import com.kgc.entity.CwCategory;
import com.kgc.entity.CwInvoice;

import java.util.List;

public interface CwInvoiceDao {

    List<CwInvoice> getCwInvoice(CwInvoice cwInvoice);

    List<CwCategory> getCategoryString();
}
