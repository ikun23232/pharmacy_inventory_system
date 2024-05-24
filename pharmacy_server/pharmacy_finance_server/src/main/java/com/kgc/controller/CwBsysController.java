package com.kgc.controller;

import com.kgc.entity.CwBsys;
import com.kgc.service.CwBsysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class CwBsysController {

    @Autowired
    private CwBsysService cwBsysService;

    @RequestMapping("/getCwbsysList")
    public Object getCwbsysList(@RequestBody CwBsys cwBsys, @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        return cwBsysService.getCwbsysList(cwBsys, pageNum, pageSize);
    }

}
