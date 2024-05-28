package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.OrderStatistics;

import javax.servlet.http.HttpServletResponse;

public interface OrderStatisticsService extends IService<OrderStatistics> {

    public Message getSaleOrderStatisticsList(OrderStatistics orderStatistics);
    public Message getRefundOrderStatisticsList(OrderStatistics orderStatistics);

    public Message getAllSaleOrderStatisticsList(OrderStatistics orderStatistics);
    public Message getAllRefundOrderStatisticsList(OrderStatistics orderStatistics);

    public Message getSaleOrderDetailStatisticsList(OrderStatistics orderStatistics);
    public Message getRefundOrderDetailStatisticsList(OrderStatistics orderStatistics);

    public Message getALLSaleOrderDetailStatisticsList(OrderStatistics orderStatistics);

    public void saleOrderStatisticsExcel(OrderStatistics orderStatistics, HttpServletResponse response);

    public void refundOrderStatisticsExcel(OrderStatistics orderStatistics, HttpServletResponse response);

    public void saleOrderDetailStatisticsExcel(OrderStatistics orderStatistics, HttpServletResponse response);

    public void refundOrderDetailStatisticsExcel(OrderStatistics orderStatistics, HttpServletResponse response);

}
