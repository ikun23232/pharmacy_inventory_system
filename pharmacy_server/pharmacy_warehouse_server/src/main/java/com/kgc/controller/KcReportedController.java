package com.kgc.controller;

import com.kgc.entity.KcReported;
import com.kgc.entity.KcReporteddetail;
import com.kgc.entity.Message;
import com.kgc.service.KcReportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class KcReportedController {

    @Autowired
    private KcReportedService kcReportedService;

    @RequestMapping("/getKcReportedList")//库存报损列表
    public Message getKcReportedList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestBody KcReported kcReported)
    {
        return kcReportedService.getKcReportedList(kcReported,pageNum,pageSize);
    }
    @RequestMapping("/getCodeByCode")//根据code查询code
    public Message getKcReportedListByCode(String code)
    {
        return kcReportedService.getCodeByCode(code);
    }

    @RequestMapping("/getStorehouseList")//仓库列表
    public Message getStorehouseList()
    {
        return kcReportedService.getStorehouseList();
    }
    @RequestMapping("/getReportedType")//报损类型
    public Message getReportedType()
    {
        return kcReportedService.getReportedType();
    }
    @RequestMapping("/addKcReported")//添加库存报损
    public Message addKcReported(@RequestBody KcReported kcReported)
    {
        return kcReportedService.addKcReported(kcReported);
    }
    @RequestMapping("/addKcReportedAll")//添加库存报损明细
    public Message addKcReportedAll(@RequestBody Map map)
    {
        return kcReportedService.addKcReportedAndDetail(map);
    }
    @RequestMapping("/updateReportedByCode")//修改库存报损
    public Message updateReportedByCode(@RequestBody Map map){
        return kcReportedService.updateReportedAndDetail(map);
    }
    @RequestMapping("/delKcReportedAndDetailByCode")//删除库存报损
    public Message delKcReportedAndDetailByCode(@RequestParam("code") String code){
        return kcReportedService.delKcReportedAndDetailByCode(code);
    }



}
