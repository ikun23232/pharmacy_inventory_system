package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.ProcPage;
import com.kgc.service.ProcurementOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/getCgddOrder")
    public Message getCgddOrder(@RequestBody ProcPage procPage) {
        logger.debug("ProcurementOrderController getCgddOrder procPage:" + procPage);
        CgddOrder cgddOrder = new CgddOrder();
        cgddOrder.setCode(procPage.getCode());
        cgddOrder.setBeginTime(procPage.getBeginTime());
        cgddOrder.setOrderStatus(procPage.getOrderStatus());
        cgddOrder.setProviderId(procPage.getProviderId());
        cgddOrder.setType(procPage.getType());
        cgddOrder.setEndTime(procPage.getEndTime());
        Page page = new Page();
        page.setCurrentPageNo(procPage.getPageNum());
        page.setPageSize(procPage.getPageSize());
        Message message = procurementOrderService.getCgddOrder(cgddOrder,page);
        return message;
    }

    @RequestMapping("/addCgddOrder")
    public Message addCgddOrder(@RequestBody Map map) {
        CgddOrder cgddOrder = JSON.parseObject(JSON.toJSONString(map.get("cgddOrder")),CgddOrder.class);
        Message message = procurementOrderService.addCgddOrder(cgddOrder);
        return message;
    }

    @RequestMapping("/deleteById")
    public Message deleteById(int id) {
        Message message = procurementOrderService.deleteById(id);
        return message;
    }

    @RequestMapping("/setVoidState")
    public Message setVoidState(CgddOrder cgddOrder) {
        Message message = procurementOrderService.setVoidState(cgddOrder);
        return message;
    }

    @RequestMapping("/getCgddByCode")
    public Message getCgddByCode(CgddOrder cgddOrder) {
        Message message = procurementOrderService.getCgddByCode(cgddOrder);
        return message;
    }

    @RequestMapping("/updateCgddById")
    public Message updateCgddById(@RequestBody Map map) {
        CgddOrder cgddOrder = JSON.parseObject(JSON.toJSONString(map.get("cgddOrder")),CgddOrder.class);
        Message message = procurementOrderService.updateCgddById(cgddOrder);
        return message;
    }

    @RequestMapping("/auditingOrder")
    public Message auditingOrder(@RequestBody Map map) {
        CgddOrder cgddOrder = JSON.parseObject(JSON.toJSONString(map.get("cgddOrder")),CgddOrder.class);
        Message message = procurementOrderService.auditingOrder(cgddOrder);
        return message;
    }
}
