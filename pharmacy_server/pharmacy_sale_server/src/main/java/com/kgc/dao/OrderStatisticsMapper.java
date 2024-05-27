package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.OrderStatistics;
import com.kgc.entity.XsOrder;
import com.kgc.vo.OrderStatisticsVo;
import com.kgc.vo.RefundOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderStatisticsMapper extends BaseMapper<OrderStatistics> {

    public List<OrderStatistics> getSaleOrderStatisticsList(@Param("orderYear") String orderYear);

    public List<OrderStatistics> getRefundOrderStatisticsList(@Param("orderYear") String orderYear);

    public List<OrderStatistics> getSaleOrderDetailStatisticsList(OrderStatistics orderStatistics);

    public List<OrderStatistics> getRefundOrderDetailStatisticsList(OrderStatistics orderStatistics);

    public List<OrderStatisticsVo> getSaleOrderStatisticsVoList(OrderStatistics orderStatistics);
    public List<OrderStatisticsVo> getRefundOrderStatisticsVoList(OrderStatistics orderStatistics);


}
