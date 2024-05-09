package com.kgc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderMapper, XsOrder> implements SaleOrderService {
    @Autowired
    private SaleOrderMapper saleOrderMapper;


    @Override
    public Message getSaleOrderListByPage(XsOrder xsOrder) {
        Message message=new Message();
        int currentPage= xsOrder.getCurrentPage();
        PageHelper.startPage(currentPage,5);
        List<XsOrder> xsOrderList=saleOrderMapper.getSaleOrderListByPage(xsOrder);
        PageInfo<XsOrder> pageInfo=new PageInfo<>(xsOrderList);
        if(pageInfo!=null){
            message.setCode("200");
            message.setData(pageInfo);
        }
        return message;
    }

    @Override
    public Message addSaleOrder(XsOrder xsOrder) {
        Message message=new Message();
        int count=saleOrderMapper.insert(xsOrder);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message getSaleOrderById(String orderNo) {
        Message message=new Message();
//        XsOrder xsOrder=saleOrderMapper.selectById(orderNo);
//        if(xsOrder!=null){
//            message.setCode("200");
//            message.setData(xsOrder);
//        }
        return message;
    }

    @Override
    public Message updateSaleOrder(XsOrder xsOrder) {
        Message message=new Message();
        int count=saleOrderMapper.updateById(xsOrder);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message deleteSaleOrder(String orderNo) {
        Message message=new Message();
        int count=saleOrderMapper.deleteById(orderNo);
        if(count>0){
            message.setCode("200");
        }
        return message;
    }

    @Override
    public Message cancelSaleOrder(String orderNo) {
        return null;
    }


}
