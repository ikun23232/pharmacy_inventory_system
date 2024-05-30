package com.kgc.remote;

import com.kgc.entity.BaseMedicine;
import com.kgc.entity.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface SaleOrderRemote {
    @RequestMapping("/getSaleOrderDetailByOrderNo")
    @ResponseBody
    public Message getSaleOrderDetailByOrderNo(@RequestParam("orderNo") String orderNo);

    @RequestMapping("/getSaleOrderDetailListByOrderNo")
    @ResponseBody
    public List<BaseMedicine> getSaleOrderDetailListByOrderNo(@RequestParam("orderNo") String orderNo);
}
