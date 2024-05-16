package com.kgc.controller;

import com.kgc.entity.KcDispatch;
import com.kgc.entity.Message;
import com.kgc.service.KcDispatchService;
import com.kgc.vo.DispatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/14 15:19
 */
@RestController
@RequestMapping("/warehouse")
public class KcDispatcjController {
    @Autowired
    private KcDispatchService kcDispatchService;

    @RequestMapping("/getKcDispathList")
    public Message getKcDispathList(@RequestBody DispatchVO dispatchVO){
        Message kcDispathList = kcDispatchService.getKcDispathList(dispatchVO);
        return kcDispathList;
    }
}
