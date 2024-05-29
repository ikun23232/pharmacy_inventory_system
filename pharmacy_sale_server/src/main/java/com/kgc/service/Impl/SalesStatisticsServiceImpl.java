package com.kgc.service.Impl;

import com.kgc.dao.SalesStatisticsMapper;
import com.kgc.entity.Message;
import com.kgc.entity.SalesStatistics;
import com.kgc.service.SalesStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SalesStatisticsServiceImpl implements SalesStatisticsService {

    @Resource
    private SalesStatisticsMapper salesStatisticsMapper;

    @Override
    public Message getSalesStatistics() {
        SalesStatistics salesStatistics = salesStatisticsMapper.getSalesStatistics();
        if (salesStatistics != null) {
            return Message.success(salesStatistics);
        }
        return Message.error();
    }
}
