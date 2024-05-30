package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/sale")
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;

    @RequestMapping("/getSaleOrderListByPage")
    @ResponseBody
    public Message getSaleOrderListByPage(@RequestBody XsOrder xsOrder) {
        Message message=saleOrderService.getSaleOrderListByPage(xsOrder);
        return message;
    }
    @RequestMapping("/addSaleOrder")
    @ResponseBody
    public Message addSaleOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = saleOrderService.addSaleOrder(xsOrder);
        return message;
    }

    @RequestMapping("/saveSaleOrder")
    @ResponseBody
    public Message saveSaleOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = saleOrderService.saveSaleOrder(xsOrder);
        return message;
    }

    @RequestMapping("/getSaleOrderByOrderNo")
    @ResponseBody
    public Message getSaleOrderByOrderNo(@RequestParam("orderNo") String orderNo) {
        Message message=saleOrderService.getSaleOrderByOrderNo(orderNo);
        return message;
    }

    @RequestMapping("/updateSaleOrder")
    @ResponseBody
    public Message updateSaleOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = saleOrderService.updateSaleOrder(xsOrder);
        return message;
    }

    @RequestMapping("/saveUpdateSaleOrder")
    @ResponseBody
    public Message saveUpdateSaleOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = saleOrderService.saveUpdateSaleOrder(xsOrder);
        return message;
    }

    @RequestMapping("/deleteSaleOrder")
    @ResponseBody
    public Message deleteSaleOrder(@RequestParam("orderNo") String orderNo) {
        Message message=saleOrderService.deleteSaleOrder(orderNo);
        return message;
    }

    @RequestMapping("/cancelSaleOrder")
    @ResponseBody
    public Message cancelSaleOrder(@RequestParam("orderNo") String orderNo) {
        Message message=saleOrderService.cancelSaleOrder(orderNo);
        return message;
    }

    @RequestMapping("/saleOrderExcel")
    public void saleOrderExcel(@RequestBody XsOrder xsOrder, HttpServletResponse response) {
        saleOrderService.saleOrderExcel(xsOrder,response);
    }
}
