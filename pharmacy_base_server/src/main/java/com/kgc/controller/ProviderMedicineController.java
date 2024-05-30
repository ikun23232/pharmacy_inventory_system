package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.ProviderMedicine;
import com.kgc.service.ProviderMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/30 19:07
 */
@RestController
@RequestMapping("/base")
public class ProviderMedicineController {
    @Autowired
    private ProviderMedicineService providerMedicineService;
    @RequestMapping("/addProviderMedicine")
    public Message addProviderMedicine(@RequestBody ProviderMedicine providerMedicine) {
        Message message = providerMedicineService.addProviderMedicine(providerMedicine);
        return message;
    }

    @RequestMapping("/checkProviderExits")
    public Message checkProviderExits(@RequestBody ProviderMedicine providerMedicine) {
        Message message = providerMedicineService.checkProviderExits(providerMedicine);
        return message;
    }
}
