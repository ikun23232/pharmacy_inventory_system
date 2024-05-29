package com.kgc.providerContorller;

import com.kgc.entity.CgddOrder;
import com.kgc.entity.Message;
import com.kgc.remote.ProcurementOrderRemote;
import com.kgc.service.ProcurementOrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProcurementOrderFeignControllor implements ProcurementOrderRemote {

    @Resource
    private ProcurementOrderService procurementOrderService;

    @Override
    public Message updateCgddIsPayById(@RequestBody CgddOrder cgddOrder) {
        Message message = procurementOrderService.updateCgddIsPayById(cgddOrder);
        return message;
    }
}
