package com.kgc.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.kgc.annotation.Log;
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
    @Log("调度入库删除")
    public Message delKcDistoware(int id) {
        StpUtil.checkPermission("aQuS3wCq90");
        Message message = kcDistowareService.delKcDistoware(id);
        return message;
    }
    @RequestMapping("/ddrkExcel")
    @Log("调度入库导出")
    public Message ddckExcel(@RequestBody KcDistoware kcDistoware, HttpServletResponse response) {
        StpUtil.checkPermission("QS1d4XPaZ9");
        kcDistowareService.ddrkExcel(kcDistoware,response);
        return Message.success(null);
    }
    @RequestMapping("/getKcDistowareOrder")
    public Message getKcDistowareOrder(int id) {
        Message kcDisfromwareOrder = kcDistowareService.getKcDistowareOrder(id);
        return kcDisfromwareOrder;
    }
}

