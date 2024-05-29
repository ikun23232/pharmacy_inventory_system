package com.kgc.remote;

import com.kgc.entity.Message;
import com.kgc.entity.OrderMedicine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/28 14:52
 */
public interface PublicOMedicineRemote {
    @RequestMapping("addMedicineOrder")
    @ResponseBody
    public Message addMedicineOrder(@RequestBody OrderMedicine orderMedicine);

    @RequestMapping("deleteMediciOrder")
    @ResponseBody
    Message deleteMediciOrder(@RequestBody Map map);
    @RequestMapping("selectMaxYourField")
    @ResponseBody
    Message selectMaxYourField();
}
