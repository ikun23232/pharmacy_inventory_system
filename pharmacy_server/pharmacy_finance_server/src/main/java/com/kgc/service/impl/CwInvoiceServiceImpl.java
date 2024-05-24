package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwInvoiceDao;
import com.kgc.entity.CwCategory;
import com.kgc.entity.CwInvoice;
import com.kgc.entity.Message;
import com.kgc.service.CwInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CwInvoiceServiceImpl implements CwInvoiceService {

    @Autowired
    private CwInvoiceDao cwInvoiceDao;

    @Override
    public Message getCwInvoice(CwInvoice cwInvoice, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CwInvoice> list=cwInvoiceDao.getCwInvoice(cwInvoice);
        PageInfo<CwInvoice> pageInfo=new PageInfo<>(list);
        if(list.size()>0){
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getCategoryString() {
        List<CwCategory> list=cwInvoiceDao.getCategoryString();
        if(list!=null){
            return Message.success(list);
        }
        return Message.error();
    }
}
