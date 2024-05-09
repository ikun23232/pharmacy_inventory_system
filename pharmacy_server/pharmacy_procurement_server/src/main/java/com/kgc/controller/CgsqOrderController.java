package com.kgc.controller;


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


}

