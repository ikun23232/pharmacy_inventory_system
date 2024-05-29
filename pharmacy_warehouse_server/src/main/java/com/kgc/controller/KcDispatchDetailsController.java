package com.kgc.controller;

import com.kgc.entity.KcDispatchdetails;
import com.kgc.entity.Message;
import com.kgc.service.KcDispatchDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/21 8:56
 */
@RestController
@RequestMapping("/warehouse")
public class KcDispatchDetailsController {

    @Autowired
    private KcDispatchDetailService kcDispatchDetailService;

    @RequestMapping("/getKcDetailsList")
    public Message getKcDetailsList(String dispatchCode) {
        Message message = kcDispatchDetailService.getKcDetailsList(dispatchCode);
        return message;
    }
}
