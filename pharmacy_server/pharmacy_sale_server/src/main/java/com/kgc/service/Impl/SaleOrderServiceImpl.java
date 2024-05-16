package com.kgc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;
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
    public Message addSaleOrder(XsOrder xsOrder) {
        xsOrder.setCreateBy(1);
        xsOrder.setUpdateBy(1);
        xsOrder.setType(0);
        xsOrder.setUpdateDate(xsOrder.getOrderDate());
        xsOrder.setEditStatus(1);
        int count=saleOrderMapper.insert(xsOrder);
        List<OrderMedicine> checkedDetail=xsOrder.getCheckedDetail();
        boolean result=true;
        for(OrderMedicine orderMedicine:checkedDetail){
            orderMedicine.setCode(xsOrder.getOrderNo());
            int count2=saleOrderMapper.addSaleOrderDetail(orderMedicine);
            if(count2<0){
                result=false;
            }
        }
        if(count>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message saveSaleOrder(XsOrder xsOrder) {
        xsOrder.setCreateBy(1);
        xsOrder.setUpdateBy(1);
        xsOrder.setType(0);
        xsOrder.setUpdateDate(xsOrder.getOrderDate());
        xsOrder.setEditStatus(0);
        int count=saleOrderMapper.insert(xsOrder);
        boolean result=true;
        List<OrderMedicine> checkedDetail=xsOrder.getCheckedDetail();
        for(OrderMedicine orderMedicine:checkedDetail){
            orderMedicine.setCode(xsOrder.getOrderNo());
            int count2=saleOrderMapper.addSaleOrderDetail(orderMedicine);
            if(count2<0){
                result=false;
            }
        }
        if(count>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
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
        xsOrder.setEditStatus(1);
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=saleOrderMapper.update(xsOrder, updateWrapper);
//        int count=saleOrderMapper.updateSaleOrder(xsOrder);

        List<OrderMedicine> checkedDetail=xsOrder.getCheckedDetail();
        boolean result=true;
        if(checkedDetail!=null){
            for(OrderMedicine orderMedicine:checkedDetail){
                orderMedicine.setCode(xsOrder.getOrderNo());
                int count2=saleOrderMapper.updateSaleOrderDetail(orderMedicine);
                if(count2<0){
                    result=false;
                }
            }
        }
        if(count>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message saveUpdateSaleOrder(XsOrder xsOrder) {
        xsOrder.setEditStatus(0);
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=saleOrderMapper.update(xsOrder, updateWrapper);
//        int count=saleOrderMapper.updateSaleOrder(xsOrder);

        List<OrderMedicine> checkedDetail=xsOrder.getCheckedDetail();
        boolean result=true;
        if(checkedDetail!=null){
            for(OrderMedicine orderMedicine:checkedDetail){
                orderMedicine.setCode(xsOrder.getOrderNo());
                int count2=saleOrderMapper.updateSaleOrderDetail(orderMedicine);
                if(count2<0){
                    result=false;
                }
            }
        }
        if(count>0&&result){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message deleteSaleOrder(String orderNo) {
        QueryWrapper<XsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("orderNo",orderNo);
        int count=saleOrderMapper.delete(queryWrapper);
        int count2=saleOrderMapper.deleteOrderDetailByOrderNo(orderNo);
        if(count>0&&count2>0){
            return Message.success();
        }else{
            return Message.error();
        }
    }

    @Override
    public Message cancelSaleOrder(String orderNo) {
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",orderNo);
        updateWrapper.set("cancelStatus", 1);
        int count=saleOrderMapper.update(null, updateWrapper);
        int count2=saleOrderMapper.cancelOrderDetailByOrderNo(orderNo);
        if(count>0&&count2>0){
            return Message.success();
        }else{
            return Message.error();
        }

    }


}
