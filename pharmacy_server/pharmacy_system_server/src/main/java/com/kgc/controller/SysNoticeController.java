package com.kgc.controller;


import com.kgc.entity.Message;
import com.kgc.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Controller
@RequestMapping("/sys")
public class SysNoticeController {

    @Autowired
    private SysNoticeService sysNoticeService;

    @RequestMapping("/getSysNoticeList")
    @ResponseBody
    public Message getSysNoticeList(){
        Message message=sysNoticeService.getSysNoticeList();
        return message;
    }

}

