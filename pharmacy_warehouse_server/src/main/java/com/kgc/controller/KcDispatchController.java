package com.kgc.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kgc.annotation.Log;
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
    @Log("库存调度添加")
    public Message addKcDispatch(@RequestBody Map map){
        StpUtil.checkPermission("kcdd:tianjia");
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
    @Log("库存调度修改")
    public Message updateDispatchBy(@RequestBody Map map) {
        StpUtil.checkPermission("kcdd:xiugai");
        KcDispatch kcDispatch = JSON.parseObject(JSON.toJSONString(map.get("KcDispatch")),KcDispatch.class);
        Message kcDispathList = kcDispatchService.updateDispatchBy(kcDispatch);
        return kcDispathList;
    }

    @RequestMapping("/auditingDispatch")
    @Log("库存调度审核")
    public Message auditingDispatch(@RequestBody Map map) {
        StpUtil.checkPermission("kcdd:autiting");
        KcDispatch kcDispatch = JSON.parseObject(JSON.toJSONString(map.get("KcDispatch")),KcDispatch.class);
        Message kcDispathList = kcDispatchService.auditingDispatch(kcDispatch);
        return kcDispathList;
    }

    @RequestMapping("/deleteDispatch")
    @Log("库存调度删除")
    public Message deleteDispatch(Integer id) {
        StpUtil.checkPermission("kcdd:delete");
        Message message = kcDispatchService.deleteDispatch(id);
        return message;
    }

    @RequestMapping("/updateVoidStatus")
    @Log("库存调度作废")
    public Message updateVoidStatus(int id) {
        StpUtil.checkPermission("kcdd:voidstatus");
        Message message = kcDispatchService.updateVoidStatus(id);
        return message;
    }

    @RequestMapping("/excelDispatch")
    @Log("库存调度导出")
    public void excelDispatch(DispatchVO dispatchVO, HttpServletResponse response){
        StpUtil.checkPermission("kcdd:excel");
        kcDispatchService.excelDispatch(dispatchVO,response);
    }
}
