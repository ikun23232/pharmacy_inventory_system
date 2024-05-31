package com.kgc.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.kgc.annotation.Log;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CwCgyf;
import com.kgc.entity.Message;
import com.kgc.service.CwCgyfService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * (CwCgyf)表控制层
 * 采购应付
 * @author makejava
 * @since 2024-05-20 09:23:53
 */
@RestController
@RequestMapping("/finance")
public class CwCgyfController {

    @Resource
    private CwCgyfService cwCgyfService;

    @RequestMapping("/getCwCgyfList")
    public Message getCwCgyfList(@RequestBody CwCgyf cwCgyf,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        Message message = cwCgyfService.getCwCgyfList(cwCgyf, pageNum, pageSize);
        return message;
    }

//    @Log("采购应付付款")
    @RequestMapping("/updateCwCgyf")
    public Message updateCwCgyf(@RequestBody CwCgyf cwCgyf){
//        StpUtil.checkPermission("cw:cgyf:add");
        Message message = cwCgyfService.updateCwCgyf(cwCgyf);
        return message;
    }

    @RequestMapping("/getCwCgyfByCode")
    public Message getCwCgyfByCode(@RequestParam("code") String code){
        Message message = cwCgyfService.getCwCgyfByCode(code);
        return message;
    }

    @RequestMapping("/getProviderList")
    public Message getProviderList(){
        Message message = cwCgyfService.getProviderList();
        return message;
    }

    @RequestMapping("/getCgddByCode")
    public Message getCgddByCode(CgddOrder cgddOrder) {
        Message message = cwCgyfService.getCgddByCode(cgddOrder);
        return message;
    }

//    @Log("采购应付导出")
    @RequestMapping("/cwCgyfExcel")
    public void cwCgyfExcel(HttpServletResponse response) {
//        StpUtil.checkPermission("cw:cgyf:excel");
        cwCgyfService.cwCgyfExcel(response);
    }
}

