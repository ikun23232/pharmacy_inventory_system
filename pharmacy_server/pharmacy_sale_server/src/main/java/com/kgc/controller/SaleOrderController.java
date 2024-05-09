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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public Message addSaleOrder(XsOrder xsOrder) {
        Message message=saleOrderService.addSaleOrder(xsOrder);
        return message;
    }

    @RequestMapping("/getSaleOrderById")
    @ResponseBody
    public Message getSaleOrderById(String orderNO) {
        Message message=saleOrderService.getSaleOrderById(orderNO);
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
