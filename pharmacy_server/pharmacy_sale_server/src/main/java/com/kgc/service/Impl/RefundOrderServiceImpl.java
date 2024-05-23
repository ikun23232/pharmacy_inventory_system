package com.kgc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.RefundOrderMapper;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;
import com.kgc.entity.XsOrder;
import com.kgc.entity.XsRefundtype;
import com.kgc.service.RefundOrderService;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, XsOrder> implements RefundOrderService {

    @Autowired
    private RefundOrderMapper refundOrderMapper;
    @Autowired
    private SaleOrderMapper saleOrderMapper;

    @Override
    public Message getRefundOrderListByPage(XsOrder xsOrder) {
        int currentPage= xsOrder.getCurrentPage();
        PageHelper.startPage(currentPage,5);
        List<XsOrder> xsOrderList=refundOrderMapper.getRefundOrderListByPage(xsOrder);
        PageInfo<XsOrder> pageInfo=new PageInfo<>(xsOrderList);
        if(pageInfo!=null){
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message addRefundOrder(XsOrder xsOrder) {
        xsOrder.setCreateBy(1);
        xsOrder.setUpdateBy(1);
        xsOrder.setType(1);
        xsOrder.setUpdateDate(xsOrder.getOrderDate());
        xsOrder.setEditStatus(1);
        xsOrder.setIsCheck(0);
        int count=refundOrderMapper.insert(xsOrder);
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
    public Message checkedRefundOrder(XsOrder xsOrder) {
        if(xsOrder.getIsCheck()==1){
            xsOrder.setEditStatus(2);
        }
        UpdateWrapper<XsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("orderNo",xsOrder.getOrderNo());
        int count=refundOrderMapper.update(xsOrder, updateWrapper);
        if(count>0){
//            if(xsOrder.getIsCheck()==1){
//                //更新库存
//                //更新财务
//            }
            return Message.success();
        }else{
            return Message.error();
        }
    }


}
