package com.kgc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Message addSaleOrder(Map<String,Object> map) {
        Message message=new Message();
        XsOrder xsOrder= (XsOrder) map.get("saleOrder");
        int count=saleOrderMapper.insert(xsOrder);
        List<BaseMedicine> baseMedicineDetailList= (List<BaseMedicine>) map.get("baseMedicineDetailList");


        return message;
    }

    @Override
    public Message getSaleOrderByOrderNo(String orderNo) {
        Message message=new Message();
        XsOrder xsOrder=saleOrderMapper.getSaleOrderByOrderNo(orderNo);
        List<BaseMedicine> baseMedicineDetailList=saleOrderMapper.getSaleOrderDetailByOrderNo(orderNo);
        xsOrder.setBaseMedicineList(baseMedicineDetailList);
        if(xsOrder!=null){
            message.setCode("200");
            message.setData(xsOrder);
        }
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
