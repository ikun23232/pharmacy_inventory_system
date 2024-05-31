package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kgc.annotation.Log;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CgsqOrder;
import com.kgc.entity.Message;
import com.kgc.service.CgsqOrderService;
import com.kgc.vo.CgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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

    @RequestMapping("/getCgsqOrderByStates")
    public Message getCgsqOrderByStates(@RequestBody CgVO cgVO){
        Message message = cgsqOrderService.getCgsqOrderByStates(cgVO);
        return message;
    }
    @Log("删除采购申请订单")
    @RequestMapping("/delCgsqOrderById")
    public Message delCgsqOrderById(int id){
        StpUtil.checkPermission("kg4pp8Ylnn");
        Message message = cgsqOrderService.delCgsqOrderById(id);
        return message;
    }
    @Log("作废采购申请订单")
    @RequestMapping("/voidCgsqOrderById")
    public Message voidCgsqOrderById(int id){
        StpUtil.checkPermission("Nwl3tTKAEB");

        Message message = cgsqOrderService.voidCgsqOrder(id);
        return  message;
    }
    @Log("添加采购申请订单")
    @RequestMapping("/addCgsqOrder")
    public Message addCgsqOrder(@RequestBody Map map){
        StpUtil.checkPermission("kg4pp8Ylnn");

        CgsqOrder cgsqOrder1 = JSONObject.parseObject(JSON.toJSONString(map.get("cgsqOrder")), CgsqOrder.class);
        Message message = cgsqOrderService.addCgsqOrder(cgsqOrder1);
        return  message;
    }

    @Log("更新采购申请订单")

    @RequestMapping("/updateCgsqOrder")
    public Message updateCgsqOrder(@RequestBody Map map){
        StpUtil.checkPermission("kg4pp8Ylnn");

        CgsqOrder cgsqOrder1 = JSONObject.parseObject(JSON.toJSONString(map.get("cgsqOrder")), CgsqOrder.class);
        Message message = cgsqOrderService.updateCgsqOrder(cgsqOrder1);
        return  message;
    }
    @RequestMapping("/getCgsqOrderById")
    public Message getCgsqOrder(int id){
        Message message = cgsqOrderService.getCgsqOrder(id);
        return  message;
    }
    @Log("审核采购申请订单")
    @RequestMapping("/approveCgsqOrder")
    public Message approveCgsqOrder(int id,String approveRemark,int approveMent){
        StpUtil.checkPermission("4AkzpTVK5X");
        Message message = cgsqOrderService.approveCgsqOrder(id,approveRemark,approveMent);
        return  message;
    }

    @RequestMapping("getCgsqOrderByCode")
    public Message getCgsqOrderByCode(CgsqOrder cgsqOrder) {
        Message message = cgsqOrderService.getCgsqOrderByCode(cgsqOrder);
        return message;
    }
    @RequestMapping("getCgsqOrderListByExcel")
    public Message getCgsqOrderListByExcel() {
        Message cgsqOrderListByExcel = cgsqOrderService.getCgsqOrderListByExcel();
        return cgsqOrderListByExcel;
    }
    @Log("导出采购申请订单")
    @RequestMapping("/cgsqExcel")
    public void cgddExcel(@RequestBody CgsqOrder cgsqOrder, HttpServletResponse response) {
        StpUtil.checkPermission("XJaFv2OkJn");
        cgsqOrderService.cgsqExcel(cgsqOrder,response);
    }
}

