package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwInvoiceDao;
import com.kgc.entity.CwCategory;
import com.kgc.entity.CwInvoice;
import com.kgc.entity.Message;
import com.kgc.service.CwInvoiceService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwAccountsVO;
import com.kgc.vo.CwInvoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public Message getCwInvoiceByCode(String code) {
        CwInvoice cwInvoice=cwInvoiceDao.getCwInvoiceByCode(code);
        if(cwInvoice!=null){
            return Message.success(cwInvoice);
        }
        return Message.error();
    }

    @Override
    public Message getCategoryString() {
        List<CwCategory> list=cwInvoiceDao.getCategoryString();
        if(list!=null){
            return Message.success(list);
        }
        return Message.error();
    }

    @Override
    public void cwInvoiceExcel(HttpServletResponse response) {
        List<CwInvoiceVO> listExcel=cwInvoiceDao.getCwInvoiceVO();
        try {
            ExeclUtil.write(listExcel, CwInvoiceVO.class,response,"发票详情");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
