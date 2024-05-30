package com.kgc.controller;

import com.kgc.entity.CgStatistics;
import com.kgc.entity.Message;
import com.kgc.service.CgStatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/procurement")
public class CgStatisticsController {

    @Resource
    private CgStatisticsService cgStatisticsService;

    @RequestMapping("/getCgStatistics")
    public Message getCgStatistics()
    {
        return cgStatisticsService.getCgStatistics();
    }
}
