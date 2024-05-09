package com.kgc.controller;

import com.kgc.entity.CgddOrder;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.ProcPage;
import com.kgc.service.ProcurementOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/8 16:02
 */
@RestController
@RequestMapping("/procurement")
public class ProcurementOrderController {

    @Autowired
    private ProcurementOrderService procurementOrderService;

    @RequestMapping("/getCgddOrder")
    public Message getCgddOrder(@RequestBody ProcPage procPage) {
        CgddOrder cgddOrder = new CgddOrder();
        cgddOrder.setCode(procPage.getCode());
        cgddOrder.setBeginTime(procPage.getBeginTime());
        cgddOrder.setOrderStatus(procPage.getOrderStatus());
        cgddOrder.setProviderId(procPage.getProviderId());
        cgddOrder.setType(procPage.getType());
        cgddOrder.setEndTime(procPage.getEndTime());
        Page page = new Page();
        page.setPageNum(procPage.getPageNum());
        page.setPageSize(procPage.getPageSize());
        Message message = procurementOrderService.getCgddOrder(cgddOrder,page);
        return message;
    }
}
