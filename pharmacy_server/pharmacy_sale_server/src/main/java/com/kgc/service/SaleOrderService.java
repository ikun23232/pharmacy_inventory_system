package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;

import java.awt.image.RescaleOp;
import java.util.List;

public interface SaleOrderService extends IService<XsOrder> {

    public Message getSaleOrderListByPage(XsOrder xsOrder);

    public Message addSaleOrder(XsOrder xsOrder);

    public Message getSaleOrderById(String orderNo);

    public Message updateSaleOrder(XsOrder xsOrder);

    public Message deleteSaleOrder(String orderNo);

    public Message cancelSaleOrder(String orderNo);
}
