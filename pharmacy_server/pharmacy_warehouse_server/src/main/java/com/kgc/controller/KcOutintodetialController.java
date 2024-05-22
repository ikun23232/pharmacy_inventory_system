package com.kgc.controller;


import com.kgc.entity.KcDisfromware;
import com.kgc.entity.Message;
import com.kgc.service.KcDisfromwareService;
import com.kgc.service.KcOutintodetialService;
import com.kgc.vo.DispatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
public class KcOutintodetialController {
    @Autowired
    private KcOutintodetialService kcOutintodetialService;

    @RequestMapping("/getKcOutIntoDetailList")
    public Message getKcOutIntoDetailList(@RequestBody DispatchVO dispatchVO){
        Message kcOutinTOdetailList = kcOutintodetialService.getKcOutinTOdetailList(dispatchVO);
        return kcOutinTOdetailList;
    }
    @RequestMapping("/delKcOutinTodetail")
    public Message delKcOutinTodetail(int id){
        Message message = kcOutintodetialService.delKcOutinTodetail(id);
        return message;
    }


    @RequestMapping("/crkmxExcel")
    public Message crkmxExcel(@RequestBody DispatchVO vo, HttpServletResponse response) {
        kcOutintodetialService.crkmxExcel(vo,response);
        return Message.success(null);
    }

}

