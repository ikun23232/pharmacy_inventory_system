package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;

public interface RefundOrderService extends IService<XsOrder> {

    public Message getRefundOrderListByPage(XsOrder xsOrder);
}
