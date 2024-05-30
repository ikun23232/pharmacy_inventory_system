package com.kgc.providerController;

import com.kgc.entity.Message;
import com.kgc.remote.SaleOrderRemote;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SaleOrderFeignController implements SaleOrderRemote {
    @Autowired
    private SaleOrderService saleOrderService;
    @Override
    public Message getSaleOrderDetailByOrderNo(String orderNo) {
        Message message=saleOrderService.getSaleOrderDetailByOrderNo(orderNo);
        return message;
    }
}
