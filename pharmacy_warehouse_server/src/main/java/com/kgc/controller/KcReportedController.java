package com.kgc.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.kgc.annotation.Log;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.KcReported;
import com.kgc.entity.KcReportedfromware;
import com.kgc.entity.Message;
import com.kgc.service.KcReportedService;
import com.kgc.utils.ExcelUtils;
import com.kgc.vo.KcReportedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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


    @Log("添加库存报损")
    @RequestMapping("/addKcReportedAll")//添加库存报损明细
    public Message addKcReportedAll(@RequestBody Map map)
    {
//        List<String> permissionList = StpUtil.getPermissionList();
//        System.out.println(permissionList);
        StpUtil.checkPermission("wa:kcbc:add");
        return kcReportedService.addKcReportedAndDetail(map);
    }

    @Log("修改库存报损")
    @RequestMapping("/updateReportedByCode")//修改库存报损
    public Message updateReportedByCode(@RequestBody Map map){
        StpUtil.checkPermission("wa:kcbc:update");
        return kcReportedService.updateReportedAndDetail(map);
    }

    @Log("删除库存报损")
    @RequestMapping("/delKcReportedAndDetailByCode")//删除库存报损
    public Message delKcReportedAndDetailByCode(@RequestParam("code") String code){
        StpUtil.checkPermission("wa:kcbc:del");
        return kcReportedService.delKcReportedAndDetailByCode(code);
    }

    @RequestMapping("/getKcReportedfromware")//获取库存报损入库列表
    public Message getKcReportedfromware(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestBody KcReportedfromware kcReportedfromware){
        return kcReportedService.getKcReportedfromware(kcReportedfromware,pageNum,pageSize);
    }


    @RequestMapping("/getAllKcReported")//获取库存报损
    public void getAllKcReported(HttpServletResponse response) throws IOException {
        List<KcReported> data = kcReportedService.getAllKcReported();
        ExcelUtils.exportToExcel(response, data, "reported_data");
    }

    @RequestMapping("/getKcReportedByCode")
    public Message getKcReportedByCode(@RequestParam("code") String code)
    {
        return kcReportedService.getKcReportedByCode(code);
    }


    @Log("报损导出")
    @RequestMapping("/kcReportedExcel")
    public void kcReportedExcel(@RequestBody KcReportedVO kcReportedVO, HttpServletResponse response) {
        StpUtil.checkPermission("wa:kcbs:excel");
        kcReportedService.kcReportedExcel(kcReportedVO, response);
    }

//    @Log("报损出库库导出")
    @RequestMapping("/kcReportedfromwareExcel")
    public void kcReportedfromwareExcel(HttpServletResponse response) {
//        StpUtil.checkPermission("wa:bsck:excel");
        kcReportedService.kcReportedfromwareExcel(response);
    }

    @RequestMapping("/addKcReportedAllByPk")
    public Message addKcReportedAllByPk() {
        // 创建外部Map
        Map<String, Object> map = new HashMap<>();

        // 创建内部Map，包含theData所需的键值对
        Map<String, Object> theData = new HashMap<>();

        // 设置storehouseId
        theData.put("storehouseId", 101); // 假设仓库ID为1

        // 设置documenterBy
        theData.put("documenterBy", 1); // 假设记录人ID为2

        // 创建药品列表
        List<Map<String, Object>> list = new ArrayList<>();

        // 添加药品信息到列表
        Map<String, Object> medicineInfo1 = new HashMap<>();
        medicineInfo1.put("reportedNum", 100); // 假设报告数量为10
        medicineInfo1.put("medicineId", 101); // 假设药品ID为101
        list.add(medicineInfo1);

        // 可以继续添加更多药品信息

        // 将药品列表添加到theData Map中
        theData.put("list", list);

        // 将theData Map添加到外部Map中
        map.put("theData", theData);
        Message message =kcReportedService.addKcReportedAllByPk(map);
        return message;
    }

}
