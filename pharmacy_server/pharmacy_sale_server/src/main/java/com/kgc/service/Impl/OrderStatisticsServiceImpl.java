package com.kgc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.OrderStatisticsMapper;
import com.kgc.entity.Message;
import com.kgc.entity.OrderStatistics;
import com.kgc.entity.XsOrder;
import com.kgc.service.OrderStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatisticsServiceImpl extends ServiceImpl<OrderStatisticsMapper, OrderStatistics> implements OrderStatisticsService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
    @Override
    public Message getSaleOrderStatisticsList(OrderStatistics orderStatistics) {
        PageHelper.startPage(orderStatistics.getCurrentPage(),5);
        List<OrderStatistics> orderStatisticsList=orderStatisticsMapper.getSaleOrderStatisticsList(orderStatistics.getOrderYear());
        PageInfo<OrderStatistics> pageInfo=new PageInfo<>(orderStatisticsList);
        if(pageInfo!=null){
           return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getRefundOrderStatisticsList(OrderStatistics orderStatistics) {
        PageHelper.startPage(orderStatistics.getCurrentPage(),5);
        List<OrderStatistics> orderStatisticsList=orderStatisticsMapper.getRefundOrderStatisticsList(orderStatistics.getOrderYear());
        PageInfo<OrderStatistics> pageInfo=new PageInfo<>(orderStatisticsList);
        if(pageInfo!=null){
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getAllSaleOrderStatisticsList(OrderStatistics orderStatistics) {
        List<OrderStatistics> orderStatisticsList=orderStatisticsMapper.getSaleOrderStatisticsList(orderStatistics.getOrderYear());
        if(orderStatisticsList!=null){
            return Message.success(orderStatisticsList);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getAllRefundOrderStatisticsList(OrderStatistics orderStatistics) {
        List<OrderStatistics> orderStatisticsList=orderStatisticsMapper.getRefundOrderStatisticsList(orderStatistics.getOrderYear());
        if(orderStatisticsList!=null){
            return Message.success(orderStatisticsList);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getSaleOrderDetailStatisticsList(OrderStatistics orderStatistics) {
        PageHelper.startPage(orderStatistics.getCurrentPage(),5);
        List<OrderStatistics> orderStatisticsList=orderStatisticsMapper.getSaleOrderDetailStatisticsList(orderStatistics);
        PageInfo<OrderStatistics> pageInfo=new PageInfo<>(orderStatisticsList);
        if(pageInfo!=null){
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getRefundOrderDetailStatisticsList(OrderStatistics orderStatistics) {
        PageHelper.startPage(orderStatistics.getCurrentPage(),5);
        List<OrderStatistics> orderStatisticsList=orderStatisticsMapper.getRefundOrderDetailStatisticsList(orderStatistics);
        PageInfo<OrderStatistics> pageInfo=new PageInfo<>(orderStatisticsList);
        if(pageInfo!=null){
            return Message.success(pageInfo);
        }else{
            return Message.error();
        }
    }

    @Override
    public Message getALLSaleOrderDetailStatisticsList(OrderStatistics orderStatistics) {
        List<OrderStatistics> orderStatisticsList=orderStatisticsMapper.getSaleOrderDetailStatisticsList(orderStatistics);
        if(orderStatisticsList!=null){
            return Message.success(orderStatisticsList);
        }else{
            return Message.error();
        }
    }
}
