package com.kgc.controller;

import com.kgc.entity.KcMedicine;
import com.kgc.entity.Message;
import com.kgc.service.KcMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
public class KcMedicineController {

    @Autowired
    private KcMedicineService kcMedicineService;

    @RequestMapping("/getKcMedicine")//库存药品列表
    public Message getKcMedicine(@RequestBody KcMedicine kcMedicine, @RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize)
    {
        return kcMedicineService.getKcMedicine(kcMedicine,pageNum,pageSize);
    }

}
