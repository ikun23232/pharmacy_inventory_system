package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    public Message updateSaleOrderByOrderNo(String orderNo);

    public Message recoverSaleOrderByOrderNo(String orderNo);

    public List<BaseMedicine> getSaleOrderDetailByOrderNo(String orderNo);

    List<BaseMedicine> getSaleOrderDetailListByOrderNo(String orderNo);
}
