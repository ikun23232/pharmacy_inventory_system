package com.kgc.providerController;

import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.remote.SaleOrderRemote;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SaleOrderFeignController implements SaleOrderRemote {
    @Autowired
    private SaleOrderService saleOrderService;
    @Override
    public List<BaseMedicine> getSaleOrderDetailByOrderNo(String orderNo) {
        List<BaseMedicine> baseMedicineList=saleOrderService.getSaleOrderDetailByOrderNo(orderNo);
        return baseMedicineList;
    }
    @Override
    public List<BaseMedicine> getSaleOrderDetailListByOrderNo(String orderNo){
        return saleOrderService.getSaleOrderDetailListByOrderNo(orderNo);
    }
}
