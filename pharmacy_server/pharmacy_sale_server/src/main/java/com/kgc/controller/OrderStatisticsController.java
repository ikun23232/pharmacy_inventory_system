package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.OrderStatistics;
import com.kgc.service.OrderStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/sale")
public class OrderStatisticsController {
    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @RequestMapping("/getSaleOrderStatisticsList")
    @ResponseBody
    public Message getSaleOrderStatisticsList(@RequestBody OrderStatistics orderStatistics) {
       Message message=orderStatisticsService.getSaleOrderStatisticsList(orderStatistics);
       return message;
    }
    @RequestMapping("/getRefundOrderStatisticsList")
    @ResponseBody
    public Message getRefundOrderStatisticsList(@RequestBody OrderStatistics orderStatistics) {
        Message message=orderStatisticsService.getRefundOrderStatisticsList(orderStatistics);
        return message;
    }

    @RequestMapping("/getAllSaleOrderStatisticsList")
    @ResponseBody
    public Message getAllSaleOrderStatisticsList(@RequestBody OrderStatistics orderStatistics) {
        Message message=orderStatisticsService.getAllSaleOrderStatisticsList(orderStatistics);
        return message;
    }
    @RequestMapping("/getAllRefundOrderStatisticsList")
    @ResponseBody
    public Message getAllRefundOrderStatisticsList(@RequestBody OrderStatistics orderStatistics) {
        Message message=orderStatisticsService.getAllRefundOrderStatisticsList(orderStatistics);
        return message;
    }
    @RequestMapping("/getSaleOrderDetailStatisticsList")
    @ResponseBody
    public Message getSaleOrderDetailStatisticsList(@RequestBody OrderStatistics orderStatistics) {
        Message message=orderStatisticsService.getSaleOrderDetailStatisticsList(orderStatistics);
        return message;
    }
    @RequestMapping("/getRefundOrderDetailStatisticsList")
    @ResponseBody
    public Message getRefundOrderDetailStatisticsList(@RequestBody OrderStatistics orderStatistics) {
        Message message=orderStatisticsService.getRefundOrderDetailStatisticsList(orderStatistics);
        return message;
    }
    @RequestMapping("/getALLSaleOrderDetailStatisticsList")
    @ResponseBody
    public Message getALLSaleOrderDetailStatisticsList(@RequestBody OrderStatistics orderStatistics) {
        Message message=orderStatisticsService.getALLSaleOrderDetailStatisticsList(orderStatistics);
        return message;
    }

}
