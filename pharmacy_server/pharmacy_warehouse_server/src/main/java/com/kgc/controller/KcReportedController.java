package com.kgc.controller;

import com.kgc.entity.KcReported;
import com.kgc.entity.KcReportedfromware;
import com.kgc.entity.Message;
import com.kgc.service.KcReportedService;
import com.kgc.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
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
    @RequestMapping("/getKcReportedListById")//库存报损列根据id
    public Message getKcReportedListById(@RequestParam("id")int id)
    {
        return kcReportedService.getKcReportedListById(id);
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

    @RequestMapping("/getKcReportedfromware")//获取库存报损入库列表
    public Message getKcReportedfromware(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestBody KcReportedfromware kcReportedfromware){
        return kcReportedService.getKcReportedfromware(kcReportedfromware,pageNum,pageSize);
    }

//    @RequestMapping("/getAllKcReported")//导出所有报损信息
//    public List<KcReported> getAllKcReported(){
//        return kcReportedService.getAllKcReported();
//    }

    @RequestMapping("/getAllKcReported")
    public void getAllKcReported(HttpServletResponse response) throws IOException {
        List<KcReported> data = kcReportedService.getAllKcReported();
        ExcelUtils.exportToExcel(response, data, "reported_data");
    }


}
