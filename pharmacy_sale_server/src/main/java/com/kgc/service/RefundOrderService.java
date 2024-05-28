package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;

import javax.servlet.http.HttpServletResponse;

public interface RefundOrderService extends IService<XsOrder> {

    public Message getRefundOrderListByPage(XsOrder xsOrder);

    public Message addRefundOrder(XsOrder xsOrder);

    public Message saveRefundOrder(XsOrder xsOrder);

    public Message checkedRefundOrder(XsOrder xsOrder);

    public void refundOrderExcel(XsOrder xsOrder, HttpServletResponse response);
}
