package com.kgc.service.impl;

import com.kgc.dao.CWStatisticsDao;
import com.kgc.entity.CWStatistics;
import com.kgc.entity.Message;
import com.kgc.service.CWStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CWStatisticsServiceImpl implements CWStatisticsService {

    @Resource
    private CWStatisticsDao cwStatisticsDao;

    @Override
    public Message getCWStatistics() {
        CWStatistics cwStatistics = cwStatisticsDao.getCWStatistics();
        if (cwStatistics != null) {
            return Message.success(cwStatistics);
        }
        return Message.error();
    }

    @Override
    public Message getCWStatisticsPicture() {
        List<CWStatistics> cwStatistics = cwStatisticsDao.getCWStatisticsPicture();
        if (cwStatistics != null) {
            return Message.success(cwStatistics);
        }
        return Message.error();
    }
}
