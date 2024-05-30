package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kgc.annotation.Log;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.CgrkOrder;
import com.kgc.entity.Message;
import com.kgc.service.CgrkOrderService;
import com.kgc.vo.CgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
    @Log("删除采购入库订单")
    @RequestMapping("/delCgrkOrderById")
    public Message delCgsqOrderById(int id){
        StpUtil.checkPermission("tBHxJP94jU");
        Message message = cgrkOrderService.delCgrkOrderById(id);
        return message;
    }
    @Log("作废采购入库订单")
    @RequestMapping("/voidCgrkOrderById")
    public Message voidCgsqOrderById(int id){
        StpUtil.checkPermission("jyHTAMbf4t");
        Message message = cgrkOrderService.voidCgrqOrder(id);
        return  message;
    }
    @Log("添加采购入库订单")
    @RequestMapping("/addCgrkOrder")
    public Message addCgsqOrder(@RequestBody Map map){
        StpUtil.checkPermission("y5TzgUtm9V");
        CgrkOrder cgsqOrder1 = JSONObject.parseObject(JSON.toJSONString(map.get("cgrkOrder")), CgrkOrder.class);
        Message message = cgrkOrderService.addCgrkOrder(cgsqOrder1);
        return  message;
    }


    @Log("修改采购入库订单")
    @RequestMapping("/updateCgrkOrder")
    public Message updateCgsqOrder(@RequestBody Map map){
        StpUtil.checkPermission("0StNqtqgPY");
        CgrkOrder cgsqOrder1 = JSONObject.parseObject(JSON.toJSONString(map.get("cgrkOrder")), CgrkOrder.class);
        Message message = cgrkOrderService.updateCgrkOrder(cgsqOrder1);
        return  message;
    }
    @RequestMapping("/getCgrkOrderById")
    public Message getCgsqOrder(int id){
        Message message = cgrkOrderService.getCgrqOrder(id);
        return  message;
    }
    @Log("审核采购入库订单")
    @RequestMapping("/approveCgrkOrder")
    public Message approveCgsqOrder(int id,String approveRemark,int approveMent){
        StpUtil.checkPermission("PNPA0Wn4hB");
        Message message = cgrkOrderService.approveCgrqOrder(id,approveRemark,approveMent);
        return  message;
    }

    @Log("导出采购入库订单")
    @RequestMapping("/cgrkExcel")
    public Message cgrkExcel(@RequestBody CgrkOrder cgrkOrder, HttpServletResponse response) {
        StpUtil.checkPermission("pAWKKpgprx");
        cgrkOrderService.cgrkExcel(cgrkOrder,response);
        return Message.success(null);
    }
}
