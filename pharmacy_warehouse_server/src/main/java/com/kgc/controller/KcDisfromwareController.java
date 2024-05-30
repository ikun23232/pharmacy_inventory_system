package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.kgc.annotation.Log;
import com.kgc.entity.CgrkOrder;
import com.kgc.entity.KcDisfromware;
import com.kgc.entity.Message;
import com.kgc.service.KcDisfromwareService;
import com.kgc.vo.DispatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/warehouse")
public class KcDisfromwareController {
    @Autowired
    private KcDisfromwareService kcDisfromwareService;
    @RequestMapping("/getKcDisfromwareList")
    public Message getKcDisfromwareList(@RequestBody DispatchVO vo) {
    Message kcDisfromwareList = kcDisfromwareService.getKcDisfromwareList(vo);
    return Message.success(kcDisfromwareList);
    }

    @RequestMapping("/delKcDisfromware")
    @Log("调度出库删除")
    public Message delKcDisfromware(int id) {
        StpUtil.checkPermission("ffPslIZX6P");
        Message message = kcDisfromwareService.delKcDisfromware(id);
        return message;
    }
    @RequestMapping("/ddckExcel")
    @Log("调度出库导出")
    public Message ddckExcel(@RequestBody KcDisfromware kcDisfromware, HttpServletResponse response) {
        StpUtil.checkPermission("T0Cmj2nP6i");
        kcDisfromwareService.ddckExcel(kcDisfromware,response);
        return Message.success(null);
    }
    @RequestMapping("/getKcDisfromwareOrder")
    public Message getKcDisfromwareOrder(int id) {
        Message kcDisfromwareOrder = kcDisfromwareService.getKcDisfromwareOrder(id);
        return kcDisfromwareOrder;
    }
}

