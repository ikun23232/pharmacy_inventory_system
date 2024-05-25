package com.kgc.controller;


import com.kgc.entity.KcDisfromware;
import com.kgc.entity.KcDistoware;
import com.kgc.entity.Message;
import com.kgc.service.KcDisfromwareService;
import com.kgc.service.KcDistowareService;
import com.kgc.vo.DispatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
public class KcDistowareController {
    @Autowired
    private KcDistowareService kcDistowareService;
    @RequestMapping("/getKcDistowareList")
    public Message getKcDistowareList(@RequestBody DispatchVO vo) {
    Message kcDisfromwareList = kcDistowareService.getKcDistowareList(vo);
    return kcDisfromwareList;
    }

    @RequestMapping("/delKcDistoware")
    public Message delKcDistoware(int id) {
        Message message = kcDistowareService.delKcDistoware(id);
        return message;
    }
    @RequestMapping("/ddckExcel")
    public Message ddckExcel(@RequestBody KcDistoware kcDistoware, HttpServletResponse response) {
        kcDistowareService.ddrkExcel(kcDistoware,response);
        return Message.success(null);
    }
    @RequestMapping("/getKcDistowareOrder")
    public Message getKcDistowareOrder(int id) {
        Message kcDisfromwareOrder = kcDistowareService.getKcDistowareOrder(id);
        return kcDisfromwareOrder;
    }
}

