package com.kgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CwAccountsDao;
import com.kgc.dao.CwInvoiceDao;
import com.kgc.entity.*;
import com.kgc.feign.SaleOrderFeign;
import com.kgc.service.CwAccountsService;
import com.kgc.service.CwInvoiceService;
import com.kgc.utils.ExeclUtil;
import com.kgc.vo.CwAccountsVO;
import com.kgc.vo.CwInvoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CwInvoiceServiceImpl extends ServiceImpl<CwInvoiceDao, CwInvoice> implements CwInvoiceService {

    @Autowired
    private CwInvoiceDao cwInvoiceDao;

    @Autowired
    private SaleOrderFeign saleOrderFeign;

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
        List<CwInvoiceVO> listExcels=new ArrayList<>();
        for (CwInvoiceVO cwInvoiceVO:listExcel){
            List<BaseMedicine> baseMedicineList = saleOrderFeign.getSaleOrderDetailListByOrderNo(cwInvoiceVO.getOrderNumber());
            cwInvoiceVO.setBaseMedicineList(baseMedicineList);
            listExcels.add(cwInvoiceVO);
        }
        try {
            ExeclUtil.write(listExcels, CwInvoiceVO.class,response,"发票详情");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Message addCwInvoice(CwInvoice cwInvoice) {
        int count=cwInvoiceDao.insert(cwInvoice);
        if(count>0){
            return Message.success();
        }else{
            return Message.error();
        }
    }
}
