package com.kgc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.RefundOrderMapper;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.RefundOrderService;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, XsOrder> implements RefundOrderService {

    @Autowired
    private RefundOrderMapper refundOrderMapper;

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
}
