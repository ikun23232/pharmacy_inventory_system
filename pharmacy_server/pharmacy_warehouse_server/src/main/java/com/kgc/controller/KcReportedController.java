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
        Map a = (Map) map.get("theData");
        KcReported kcReported = new KcReported();
        kcReported.setCode((String) a.get("code"));
        kcReported.setStorehouseId((Integer) a.get("storehouseId"));
        kcReported.setReportedTypeId((Integer) a.get("reportedTypeId"));
        kcReported.setDocumenterBy((Integer) a.get("documenterBy"));
        List list = (List) a.get("list");
        for (int i = 0; i < list.size(); i++){
            KcReporteddetail kcReporteddetail = new KcReporteddetail();
            kcReporteddetail.setReportedCode((String) a.get("code"));
            kcReporteddetail.setMedicineId((Integer) ((Map)list.get(i)).get("medicineId"));
            kcReporteddetail.setQuantity((Integer) ((Map)list.get(i)).get("reportedNum"));
            kcReporteddetail.setBatchCode((String) ((Map)list.get(i)).get("batchCode"));
            Message message = kcReportedService.addKcReporteddetail(kcReporteddetail);
            if (!message.getCode().equals("200")){
                return message;
            }
        }

        return kcReportedService.addKcReported(kcReported);
    }

    @RequestMapping("/updateReportedByCode")//修改库存报损
    public Message updateReportedByCode(@RequestBody Map map){
        Map theData=(Map)map.get("theData");
        Map kcReportedMap=(Map)theData.get("kcReported");
        KcReported kcReported = new KcReported();
        kcReported.setCode((String) kcReportedMap.get("code"));
        kcReported.setApprovalStatus(Integer.valueOf(kcReportedMap.get("approvalStatus").toString()));
        kcReported.setReportedTypeId(Integer.valueOf(kcReportedMap.get("reportedTypeId").toString()));
        kcReported.setApproverRemark((String) kcReportedMap.get("approverRemark"));
        kcReported.setModificationBy(Integer.parseInt(kcReportedMap.get("modificationBy").toString()));
        if (kcReported.getApprovalStatus()!=0){
            kcReported.setApproverBy(kcReported.getModificationBy());
        }
        List list = (List) theData.get("kcMedicineList");

        kcReportedService.delKcReporteddetailByCode(kcReported.getCode());

        for (int i = 0; i < list.size(); i++){
            KcReporteddetail kcReporteddetail = new KcReporteddetail();
            kcReporteddetail.setReportedCode((String) kcReportedMap.get("code"));
            kcReporteddetail.setMedicineId((Integer) ((Map)list.get(i)).get("medicineId"));
            kcReporteddetail.setQuantity((Integer) ((Map)list.get(i)).get("reportedNum"));
            kcReporteddetail.setBatchCode((String) ((Map)list.get(i)).get("batchCode"));
            Message message = kcReportedService.addKcReporteddetail(kcReporteddetail);
            if (!message.getCode().equals("200")){
                return message;
            }
        }

        Message message = kcReportedService.updateReportedByCode(kcReported);
        if (!message.getCode().equals("200")){
            return message;
        }
        return message;
    }

    @RequestMapping("/delKcReportedAndDetailByCode")//删除库存报损
    public Message delKcReportedAndDetailByCode(@RequestParam("code") String code){
        return kcReportedService.delKcReportedAndDetailByCode(code);
    }



}
