package com.kgc.controller;

import com.kgc.entity.KcReported;
import com.kgc.service.KcReportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
public class KcReportedController {

    @Autowired
    private KcReportedService kcReportedService;

    @RequestMapping("/getKcReportedList")//库存报损列表
    public Object getKcReportedList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestBody KcReported kcReported)
    {
        return kcReportedService.getKcReportedList(kcReported,pageNum,pageSize);
    }
    @RequestMapping("/getCodeByCode")//根据code查询code
    public Object getKcReportedListByCode(String code)
    {
        return kcReportedService.getCodeByCode(code);
    }

    @RequestMapping("/getStorehouseList")//仓库列表
    public Object getStorehouseList()
    {
        return kcReportedService.getStorehouseList();
    }
    @RequestMapping("/getReportedType")//报损类型
    public Object getReportedType()
    {
        return kcReportedService.getReportedType();
    }
    @RequestMapping("/addKcReported")//添加库存报损
    public Object addKcReported(@RequestBody KcReported kcReported)
    {
        return kcReportedService.addKcReported(kcReported);
    }



}
