package com.kgc.providerContorller;

import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import com.kgc.remote.SaleOutWarehouseRemote;
import com.kgc.service.SaleOutWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SaleOutWarehouseFeignController implements SaleOutWarehouseRemote {

    @Autowired
    private SaleOutWarehouseService saleOutWarehouseService;

    @Override
    public Message addSaleOutWarehouse(KcSalefromware kcSalefromware) {
        Message message=saleOutWarehouseService.addSaleOutWarehouse(kcSalefromware);
        return message;
    }


}
