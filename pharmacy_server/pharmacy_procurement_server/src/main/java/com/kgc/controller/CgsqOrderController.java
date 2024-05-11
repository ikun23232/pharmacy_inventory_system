package com.kgc.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kgc.entity.CgsqOrder;
import com.kgc.entity.Message;
import com.kgc.service.CgsqOrderService;
import com.kgc.vo.CgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/procurement")
public class CgsqOrderController {
    @Autowired
    private CgsqOrderService cgsqOrderService;
    @RequestMapping("/getCgSqOrderList")
    public Message getCgSqOrderList(@RequestBody CgVO cgVO){
        Message message = cgsqOrderService.getCgsqOrderList(cgVO);
        return message;
    }
    @RequestMapping("/delCgsqOrderById")
    public Message delCgsqOrderById(int id){
        Message message = cgsqOrderService.delCgsqOrderById(id);
        return message;
    }
    @RequestMapping("/voidCgsqOrderById")
    public Message voidCgsqOrderById(int id){
        Message message = cgsqOrderService.voidCgsqOrder(id);
        return  message;
    }
    @RequestMapping("/addCgsqOrder")
    public Message addCgsqOrder(@RequestBody Map map){
        CgsqOrder cgsqOrder1 = JSONObject.parseObject(JSON.toJSONString(map.get("cgsqOrder")), CgsqOrder.class);
        Message message = cgsqOrderService.addCgsqOrder(cgsqOrder1);
        return  message;
    }



}

