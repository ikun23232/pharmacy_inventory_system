package com.kgc.controller;


import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.service.BaseMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Controller
@RequestMapping("/base")
public class BaseMedicineController {

    @Autowired
    private BaseMedicineService baseMedicineService;

    @RequestMapping("/getBaseMedicineListByPage")
    @ResponseBody
    public Message getBaseMedicineListByPage(@RequestBody BaseMedicine baseMedicine){
        Message message=baseMedicineService.getBaseMedicineListByPage(baseMedicine);
        return message;
    }

    @RequestMapping("/addBaseMedicine")
    @ResponseBody
    public Message addBaseMedicine(@RequestBody BaseMedicine baseMedicine){
        Message message=baseMedicineService.addBaseMedicine(baseMedicine);
        return message;
    }

    @RequestMapping("/getBaseMedicineById")
    @ResponseBody
    public Message getBaseMedicineById(@RequestParam("id") int id){
        Message message=baseMedicineService.getBaseMedicineById(id);
        return message;
    }

    @RequestMapping("/updateBaseMedicineById")
    @ResponseBody
    public Message updateBaseMedicineById(@RequestBody BaseMedicine baseMedicine){
        Message message=baseMedicineService.updateBaseMedicineById(baseMedicine);
        return message;
    }
    @RequestMapping("/deleteBaseMedicineById")
    @ResponseBody
    public Message deleteBaseMedicineById(@RequestParam("id") int id){
        Message message=baseMedicineService.deleteBaseMedicineById(id);
        return message;
    }

    @RequestMapping("/getMedicineListByCode")
    @ResponseBody
    public Message getMedicineListByCode(String code){
        Message medicineListByCode = baseMedicineService.getMedicineListByCode(code);
        return medicineListByCode;
    }
    @RequestMapping("/getBaseMedicineListByProviderId")
    @ResponseBody
    public Message getBaseMedicineListByProviderId(int providerId) {
        Message baseMedicineList=baseMedicineService.getBaseMedicineListByProviderId(providerId);
        return Message.success(baseMedicineList);
    }
}

