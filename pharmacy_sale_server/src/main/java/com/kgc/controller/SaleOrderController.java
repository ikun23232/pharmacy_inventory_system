package com.kgc.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.kgc.annotation.Log;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.AlipayService;
import com.kgc.service.SaleOrderService;
import com.kgc.utils.QRcodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/sale")
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/getSaleOrderListByPage")
    @ResponseBody
    public Message getSaleOrderListByPage(@RequestBody XsOrder xsOrder) {
        Message message=saleOrderService.getSaleOrderListByPage(xsOrder);
        return message;
    }
    @Log("销售订单添加")
    @RequestMapping("/addSaleOrder")
    @ResponseBody
    public Message addSaleOrder(@RequestBody Map<String,Object> map) {
        StpUtil.checkPermission("xsdd:add");
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = saleOrderService.addSaleOrder(xsOrder);
        return message;
    }

    @Log("保存销售订单添加")
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
    @Log("销售订单修改")
    @RequestMapping("/updateSaleOrder")
    @ResponseBody
    public Message updateSaleOrder(@RequestBody Map<String,Object> map) {
        StpUtil.checkPermission("xsdd:update");
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = saleOrderService.updateSaleOrder(xsOrder);
        return message;
    }
    @Log("保存销售订单修改")
    @RequestMapping("/saveUpdateSaleOrder")
    @ResponseBody
    public Message saveUpdateSaleOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = saleOrderService.saveUpdateSaleOrder(xsOrder);
        return message;
    }
    @Log("销售订单删除")
    @RequestMapping("/deleteSaleOrder")
    @ResponseBody
    public Message deleteSaleOrder(@RequestParam("orderNo") String orderNo) {
        StpUtil.checkPermission("xsdd:delete");
        Message message=saleOrderService.deleteSaleOrder(orderNo);
        return message;
    }

    @Log("销售订单作废")
    @RequestMapping("/cancelSaleOrder")
    @ResponseBody
    public Message cancelSaleOrder(@RequestParam("orderNo") String orderNo) {
        StpUtil.checkPermission("xsdd:cancel");
        Message message=saleOrderService.cancelSaleOrder(orderNo);
        return message;
    }
    @Log("销售订单导出")
    @RequestMapping("/saleOrderExcel")
    public void saleOrderExcel(@RequestBody XsOrder xsOrder, HttpServletResponse response) {
        StpUtil.checkPermission("xsdd:excel");
        saleOrderService.saleOrderExcel(xsOrder,response);
    }

    @RequestMapping("/updateSaleOrderByOrderNo")
    @ResponseBody
    public Message updateSaleOrderByOrderNo(@RequestParam("orderNo") String orderNo) {
        Message message=saleOrderService.updateSaleOrderByOrderNo(orderNo);
        return message;
    }

    @RequestMapping("/recoverSaleOrderByOrderNo")
    @ResponseBody
    public Message recoverSaleOrderByOrderNo(@RequestParam("orderNo") String orderNo) {
        Message message=saleOrderService.recoverSaleOrderByOrderNo(orderNo);
        return message;
    }

//    @RequestMapping("getAliPayImg")
//    public void qrcode(XsOrder xsOrder, HttpServletResponse response) throws Exception {
//        Message order = alipayService.createOrder(xsOrder);
//        QRcodeUtil.encode(order.getData().toString(), response);
//    }
}
