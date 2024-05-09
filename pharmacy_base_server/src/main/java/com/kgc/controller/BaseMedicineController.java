package com.kgc.controller;


import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.service.BaseMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Controller
@RequestMapping("/baseMedicine")
public class BaseMedicineController {

    @Autowired
    private BaseMedicineService baseMedicineService;

    @RequestMapping("/getBaseMedicineList")
    @ResponseBody
    public Message getBaseMedicineList(int currentPage){
        Message message=baseMedicineService.getBaseMedicineList(currentPage);
        return message;
    }

    @RequestMapping("/addBaseMedicine")
    @ResponseBody
    public Message addBaseMedicine(BaseMedicine baseMedicine){
        Message message=baseMedicineService.addBaseMedicine(baseMedicine);
        return message;
    }

    @RequestMapping("/updateBaseMedicineById")
    @ResponseBody
    public Message updateBaseMedicineById(BaseMedicine baseMedicine){
        Message message=baseMedicineService.updateBaseMedicineById(baseMedicine);
        return message;
    }
    @RequestMapping("/deleteBaseMedicineById")
    @ResponseBody
    public Message deleteBaseMedicineById(int id){
        Message message=baseMedicineService.deleteBaseMedicineById(id);
        return message;
    }

}

