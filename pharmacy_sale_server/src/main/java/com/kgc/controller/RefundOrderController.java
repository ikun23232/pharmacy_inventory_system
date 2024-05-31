package com.kgc.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.kgc.annotation.Log;
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

    @Log("销售退款订单添加")
    @RequestMapping("/addRefundOrder")
    @ResponseBody
    public Message addRefundOrder(@RequestBody Map<String,Object> map) {
//        StpUtil.checkPermission("xstk:add");
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.addRefundOrder(xsOrder);
        return message;
    }
    @Log("保存销售退款订单添加")
    @RequestMapping("/saveRefundOrder")
    @ResponseBody
    public Message saveRefundOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.saveRefundOrder(xsOrder);
        return message;
    }

    @Log("销售退款订单修改")
    @RequestMapping("/updateRefundOrder")
    @ResponseBody
    public Message updateRefundOrder(@RequestBody Map<String,Object> map) {
//        StpUtil.checkPermission("xstk:update");
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.updateRefundOrder(xsOrder);
        return message;
    }
    @Log("保存销售退款订单修改")
    @RequestMapping("/saveUpdateRefundOrder")
    @ResponseBody
    public Message saveUpdateRefundOrder(@RequestBody Map<String,Object> map) {
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.saveUpdateRefundOrder(xsOrder);
        return message;
    }
    @Log("销售退款订单审核")
    @RequestMapping("/checkedRefundOrder")
    @ResponseBody
    public Message checkedRefundOrder(@RequestBody Map<String,Object> map) {
//        StpUtil.checkPermission("xstk:check");
        XsOrder xsOrder = JSON.parseObject(JSON.toJSONString(map.get("xsOrder")),XsOrder.class);
        Message message = refundOrderService.checkedRefundOrder(xsOrder);
        return message;
    }
    @Log("销售退款订单导出")
    @RequestMapping("/refundOrderExcel")
    public void saleOrderExcel(@RequestBody XsOrder xsOrder, HttpServletResponse response) {
//        StpUtil.checkPermission("xstk:excel");
        refundOrderService.refundOrderExcel(xsOrder,response);
    }
}
