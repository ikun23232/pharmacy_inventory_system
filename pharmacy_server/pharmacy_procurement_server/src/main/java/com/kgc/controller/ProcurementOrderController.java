package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.*;
import com.kgc.service.ProcurementOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
        cgddOrder.setApprovalStatus(procPage.getApprovalStatus());
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


    @RequestMapping("/cgddExcel")
    public Message cgddExcel(@RequestBody CgddOrder procPage, HttpServletResponse response) {
        procurementOrderService.cgddExcel(procPage,response);
        return Message.success(null);
    }


    @RequestMapping("/getCgPayCom")
    public Message getCgPayCom(@RequestParam("year") String year,@RequestParam("month") String month) {
        logger.debug("ProcurementOrderController getCgPayCom year:" + year + ",month:" + month);
        Message message = procurementOrderService.getCgPayCom(year, month);
        return message;
    }

    @RequestMapping("/getCgPayNum")
    public Message getCgPayNum(@RequestParam("year") String year) {
        logger.debug("ProcurementOrderController getCgPayNum year:" + year);
        Message message = procurementOrderService.getCgPayNum(year);
        return message;
    }
    @RequestMapping("/getCgPayNumList")
    public Message getCgPayNumList(@RequestParam("year") String year,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        logger.debug("ProcurementOrderController getCgPayNumList year:" + year + ",pageNum:" + pageNum + ",pageSize:" + pageSize);
        Message message = procurementOrderService.getCgPayNumList(year,pageNum,pageSize);
        return message;
    }

    @RequestMapping("/addcgyf")
    public Message addcgyf(@RequestBody CwCgyf cwCgyf) {
        return procurementOrderService.addcgyf(cwCgyf);
    }
}
