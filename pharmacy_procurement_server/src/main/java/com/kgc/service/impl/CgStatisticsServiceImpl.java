package com.kgc.service.impl;

import com.kgc.dao.CgStatisticsMapper;
import com.kgc.entity.CgStatistics;
import com.kgc.entity.Message;
import com.kgc.service.CgStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CgStatisticsServiceImpl implements CgStatisticsService {

    @Resource
    private CgStatisticsMapper cgStatisticsMapper;

    @Override
    public Message getCgStatistics() {
        CgStatistics cgStatistics = cgStatisticsMapper.getCgStatistics();
        if (cgStatistics != null){
            return Message.success(cgStatistics);
        }
        return Message.error();
    }
}
