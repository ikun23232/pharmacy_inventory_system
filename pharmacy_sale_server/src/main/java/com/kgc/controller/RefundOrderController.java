package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.RefundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/sale")
public class RefundOrderController {

    @Autowired
    private RefundOrderService refundOrderService;

    @RequestMapping("/getRefundOrderListByPage")
    @ResponseBody
    public Message getRefundOrderListByPage(@RequestBody XsOrder xsOrder) {
        Message message=refundOrderService.getRefundOrderListByPage(xsOrder);
        return message;
    }


    @RequestMapping("/addRefundOrder")
    @ResponseBody
    public Message addRefundOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.addRefundOrder(xsOrder);
        return message;
    }
    @RequestMapping("/saveRefundOrder")
    @ResponseBody
    public Message saveRefundOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.saveRefundOrder(xsOrder);
        return message;
    }

    @RequestMapping("/checkedRefundOrder")
    @ResponseBody
    public Message checkedRefundOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.checkedRefundOrder(xsOrder);
        return message;
    }

    @RequestMapping("/refundOrderExcel")
    public void saleOrderExcel(@RequestBody XsOrder xsOrder, HttpServletResponse response) {
        refundOrderService.refundOrderExcel(xsOrder,response);
    }
}
