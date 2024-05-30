package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.CWStatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/finance")
public class CWStatisticsController {

    @Resource
    private CWStatisticsService cwStatisticsService;


    @RequestMapping("/getCWStatistics")
    public Message getCWStatistics()
    {
        return cwStatisticsService.getCWStatistics();
    }

    @RequestMapping("/getCWStatisticsPicture")
    public Message getCWStatisticsPicture()
    {
        return cwStatisticsService.getCWStatisticsPicture();
    }
}
