package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.entity.CgddOrder;
import com.kgc.entity.KcDispatch;
import com.kgc.entity.Message;
import com.kgc.service.KcDispatchService;
import com.kgc.vo.DispatchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/14 15:19
 */
@RestController
@RequestMapping("/warehouse")
public class KcDispatchController {
    @Autowired
    private KcDispatchService kcDispatchService;

    @RequestMapping("/getKcDispathList")
    public Message getKcDispathList(@RequestBody DispatchVO dispatchVO){
        Message kcDispathList = kcDispatchService.getKcDispathList(dispatchVO);
        return kcDispathList;
    }

    @RequestMapping("/addKcDispatch")
    public Message addKcDispatch(@RequestBody Map map){
        KcDispatch kcDispatch = JSON.parseObject(JSON.toJSONString(map.get("KcDispatch")),KcDispatch.class);
        Message kcDispathList = kcDispatchService.addKcDispatch(kcDispatch);
        return kcDispathList;
    }

    @RequestMapping("/getKcDispatchById")
    public Message getKcDispatchById(KcDispatch kcDispatch) {
        Message message = kcDispatchService.getKcDispatchById(kcDispatch);
        return message;
    }

    @RequestMapping("/updateDispatchBy")
    public Message updateDispatchBy(@RequestBody Map map) {
        KcDispatch kcDispatch = JSON.parseObject(JSON.toJSONString(map.get("KcDispatch")),KcDispatch.class);
        Message kcDispathList = kcDispatchService.updateDispatchBy(kcDispatch);
        return kcDispathList;
    }

    @RequestMapping("/auditingDispatch")
    public Message auditingDispatch(@RequestBody Map map) {
        KcDispatch kcDispatch = JSON.parseObject(JSON.toJSONString(map.get("KcDispatch")),KcDispatch.class);
        Message kcDispathList = kcDispatchService.auditingDispatch(kcDispatch);
        return kcDispathList;
    }

    @RequestMapping("/deleteDispatch")
    public Message deleteDispatch(Integer id) {
        Message message = kcDispatchService.deleteDispatch(id);
        return message;
    }

    @RequestMapping("/updateVoidStatus")
    public Message updateVoidStatus(int id) {
        Message message = kcDispatchService.updateVoidStatus(id);
        return message;
    }

    @RequestMapping("/excelDispatch")
    public void excelDispatch(DispatchVO dispatchVO, HttpServletResponse response){
        kcDispatchService.excelDispatch(dispatchVO,response);
    }
}
