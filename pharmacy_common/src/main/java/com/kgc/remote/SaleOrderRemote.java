package com.kgc.remote;

import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface SaleOrderRemote {
    @RequestMapping("/getSaleOrderDetailByOrderNo")
    @ResponseBody
    public Message getSaleOrderDetailByOrderNo(@RequestParam("orderNo") String orderNo);
}
