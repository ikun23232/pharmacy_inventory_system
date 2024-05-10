package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;

import java.awt.image.RescaleOp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface SaleOrderService extends IService<XsOrder> {

    public Message getSaleOrderListByPage(XsOrder xsOrder);

    public Message addSaleOrder(Map<String, Object> map);

    public Message getSaleOrderByOrderNo(String orderNo);

    public Message updateSaleOrder(XsOrder xsOrder);

    public Message deleteSaleOrder(String orderNo);

    public Message cancelSaleOrder(String orderNo);

}
