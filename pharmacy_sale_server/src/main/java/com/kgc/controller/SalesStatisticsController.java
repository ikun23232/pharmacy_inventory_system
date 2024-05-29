package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.SalesStatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sale")
public class SalesStatisticsController {

    @Resource
    private SalesStatisticsService salesStatisticsService;

    @RequestMapping("/getSalesStatistics")
    @ResponseBody
    public Message getSalesStatistics()
    {
        return salesStatisticsService.getSalesStatistics();
    }

}
