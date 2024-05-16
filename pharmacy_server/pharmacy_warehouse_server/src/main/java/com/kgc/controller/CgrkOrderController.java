package com.kgc.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kgc.entity.CgrkOrder;
import com.kgc.entity.Message;
import com.kgc.service.CgrkOrderService;
import com.kgc.vo.CgVO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/warehouse")
public class CgrkOrderController {
    @Autowired
    private CgrkOrderService cgrkOrderService;
    @RequestMapping("/getCgrkOrderList")
    public Message getCgrkOrderList(@RequestBody CgVO cgVO){
        Message cgrkOrderService1 = cgrkOrderService.getCgrkOrderService(cgVO);
        return  cgrkOrderService1;
    }
    @RequestMapping("/delCgrkOrderById")
    public Message delCgsqOrderById(int id){
        Message message = cgrkOrderService.delCgrkOrderById(id);
        return message;
    }
    @RequestMapping("/voidCgrkOrderById")
    public Message voidCgsqOrderById(int id){
        Message message = cgrkOrderService.voidCgrqOrder(id);
        return  message;
    }
    @RequestMapping("/addCgrkOrder")
    public Message addCgsqOrder(@RequestBody Map map){
        CgrkOrder cgsqOrder1 = JSONObject.parseObject(JSON.toJSONString(map.get("cgrkOrder")), CgrkOrder.class);
        Message message = cgrkOrderService.addCgrkOrder(cgsqOrder1);
        return  message;
    }

    @RequestMapping("/updateCgrkOrder")
    public Message updateCgsqOrder(@RequestBody Map map){
        CgrkOrder cgsqOrder1 = JSONObject.parseObject(JSON.toJSONString(map.get("cgrkOrder")), CgrkOrder.class);
        Message message = cgrkOrderService.updateCgrkOrder(cgsqOrder1);
        return  message;
    }
    @RequestMapping("/getCgrkOrderById")
    public Message getCgsqOrder(int id){
        Message message = cgrkOrderService.getCgrqOrder(id);
        return  message;
    }

    @RequestMapping("/approveCgrkOrder")
    public Message approveCgsqOrder(int id,String approveRemark,int approveMent){
        Message message = cgrkOrderService.approveCgrqOrder(id,approveRemark,approveMent);
        return  message;
    }
}
