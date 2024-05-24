package com.kgc.controller;

import com.kgc.entity.CwXstk;
import com.kgc.entity.CwXsys;
import com.kgc.entity.Message;
import com.kgc.service.CwXstkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class CwXstkController {

    @Autowired
    private CwXstkService cwXstkService;

    @RequestMapping("/getXstkList")
    public Message getXstkList(@RequestBody CwXstk cwXstk, @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        return cwXstkService.getXstkList(cwXstk, pageNum, pageSize);
    }


    @RequestMapping("/getXsysList")
    public Message getXsysList(@RequestBody CwXsys cwXsys, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return cwXstkService.getXsysList(cwXsys, pageNum, pageSize);
    }

}
