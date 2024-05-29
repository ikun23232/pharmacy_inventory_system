package com.kgc.controller;

import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import com.kgc.entity.XsOrder;
import com.kgc.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/warehouse")
public class StockDetailController {

    @Autowired
    private StockDetailService stockDetailService;
    @RequestMapping("/getStockDetailListByPage")
    @ResponseBody
    public Message getStockDetailListByPage(@RequestBody BaseMedicine baseMedicine){
        Message message=stockDetailService.getStockDetailListByPage(baseMedicine);
        return message;
    }

    @RequestMapping("/getStockDetailList")
    @ResponseBody
    public Message getStockDetailList(){
        Message message=stockDetailService.getStockDetailList();
        return message;
    }

    @RequestMapping("/stockDetailExcel")
    public void saleOrderExcel(@RequestBody BaseMedicine baseMedicine,HttpServletResponse response) {
        stockDetailService.stockDetailExcel(baseMedicine,response);
    }
}

