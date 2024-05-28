package com.kgc.controller;

import com.kgc.entity.CwBsys;
import com.kgc.entity.Message;
import com.kgc.service.CwBsysService;
import com.kgc.vo.KcReportedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/finance")
public class CwBsysController {

    @Autowired
    private CwBsysService cwBsysService;

    @RequestMapping("/getCwbsysList")
    public Object getCwbsysList(@RequestBody CwBsys cwBsys, @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        return cwBsysService.getCwbsysList(cwBsys, pageNum, pageSize);
    }

    @RequestMapping("/getCwbsysByCode")
    public Message getCwbsysByCode(@RequestParam("code") String code){
        return cwBsysService.getCwbsysByCode(code);
    }

    @RequestMapping("/getStorehouseList")
    public Message getStorehouseList(){
        return cwBsysService.getStorehouseList();
    }

    @RequestMapping("/cwbsysExcel")
    public void cwbsysExcel(HttpServletResponse response){
        cwBsysService.cwbsysExcel(response);
    }



}
