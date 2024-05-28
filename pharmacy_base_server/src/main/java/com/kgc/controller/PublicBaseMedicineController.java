package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.remote.BaseMedineRemote;
import com.kgc.service.PublicBaseMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 15:51
 */
@RestController
public class PublicBaseMedicineController implements BaseMedineRemote {

    @Autowired
    private PublicBaseMedicineService publicBaseMedicineService;
    @Override
    public Message getMedicineListByCode(String code) {
        Message message = publicBaseMedicineService.getMedicineListByCode(code);
        return message;
    }

    @Override
    public Message getMedicineVOListByCode(String code) {
        Message message = publicBaseMedicineService.getMedicineVOListByCode(code);
        return message;
    }
}
