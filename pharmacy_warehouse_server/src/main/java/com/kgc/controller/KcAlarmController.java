package com.kgc.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.annotation.Log;
import com.kgc.entity.KcAlarm;
import com.kgc.entity.Message;
import com.kgc.service.KcAlarmService;
import com.kgc.vo.KcAlarmVO;
import com.kgc.vo.WarningVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/23 11:04
 */
@RestController
@RequestMapping("warehouse")
public class KcAlarmController {

    @Autowired
    private KcAlarmService kcAlarmService;

    @RequestMapping("getKcAlarmList")
    public Message getKcAlarmList(@RequestBody WarningVo warningVo) {
        Message message = kcAlarmService.getKcAlarmList(warningVo);
        return message;
    }

    @RequestMapping("deleteAlarmList")
    @Log("库存告警删除")
    public Message deleteAlarmList(Integer id) {
        StpUtil.checkPermission("kcgj:bihewoqiang");
        Message message = kcAlarmService.deleteAlarmList(id);
        return message;
    }

    @RequestMapping("updateRemind")
    public Message updateRemind(Integer id) {
        Message message = kcAlarmService.updateRemind(id);
        return message;
    }

    @RequestMapping("excelKcAlarm")
    @Log("库存告警导出")
    public void excelKcAlarm(@RequestBody KcAlarmVO kcAlarmVO, HttpServletResponse response) {
        StpUtil.checkPermission("kcgj:daochu");
        kcAlarmService.excelKcAlarm(kcAlarmVO,response);
    }

    @RequestMapping("checkWarning")
    public void checkWarning(){
        kcAlarmService.checkWarning();
    }

    @RequestMapping("getAlarmCount")
    public Message getAlarmCount() {
        Message alarmCount = kcAlarmService.getAlarmCount();
        return alarmCount;
    }
}
