package com.kgc.remote;

import com.kgc.entity.KcSalefromware;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface RefundInWarehouseRemote {

    @RequestMapping("/addRefundInWarehouse")
    @ResponseBody
    Message addRefundInWarehouse(@RequestBody KcSalefromware kcSalefromware);
}
