package com.kgc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.SaleOrderMapper;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public Message addSaleOrder(@RequestBody Map<String, Object> map) {
        Message message=saleOrderService.addSaleOrder(map);
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
    public Message updateSaleOrder(XsOrder xsOrder) {
        Message message=saleOrderService.updateSaleOrder(xsOrder);
        return message;
    }

    @RequestMapping("/deleteSaleOrder")
    @ResponseBody
    public Message deleteSaleOrder(String orderNo) {
        Message message=saleOrderService.deleteSaleOrder(orderNo);
        return message;
    }

}
