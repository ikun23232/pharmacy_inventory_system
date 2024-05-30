package com.kgc.providerController;

import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.remote.RefundInWarehouseRemote;
import com.kgc.service.RefundInWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RefundInWarehouseFeignController implements RefundInWarehouseRemote {

    @Autowired
    private RefundInWarehouseService refundInWarehouseService;

    @Override
    public Message addRefundInWarehouse(KcSalefromware kcSalefromware) {
        Message message=refundInWarehouseService.addRefundInWarehouse(kcSalefromware);
        return message;
    }
}
