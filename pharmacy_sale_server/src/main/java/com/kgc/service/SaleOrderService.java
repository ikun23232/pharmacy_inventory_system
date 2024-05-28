package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;

import javax.servlet.http.HttpServletResponse;

public interface SaleOrderService extends IService<XsOrder> {

    public Message getSaleOrderListByPage(XsOrder xsOrder);

    public Message addSaleOrder(XsOrder xsOrder);

    public Message saveSaleOrder(XsOrder xsOrder);

    public Message getSaleOrderByOrderNo(String orderNo);

    public Message updateSaleOrder(XsOrder xsOrder);

    public Message saveUpdateSaleOrder(XsOrder xsOrder);

    public Message deleteSaleOrder(String orderNo);

    public Message cancelSaleOrder(String orderNo);

    public void saleOrderExcel(XsOrder xsOrder, HttpServletResponse response);

}
