package com.kgc.controller;

import com.kgc.annotation.Log;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.SysLoginLogService;
import com.kgc.vo.SysLoginLogVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 登录日志
 *
 * @author Tellsea
 * @date 2022-12-30
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysLoginLogController  {

    @Autowired
    private final SysLoginLogService sysLoginLogService;
//    @SaCheckPermission("system:sysLoginLog:list")
    @RequestMapping("/sysLoginLog/list")
    public Message queryList(@RequestBody SysLoginLogVo entry) {
        int _currentPageNo = 1;
        if (entry.getCurrent() != null && !"".equals(entry.getCurrent())) {
            _currentPageNo = Integer.parseInt(entry.getCurrent());
        }
        Page page = new Page();
        page.setPageSize(4);
        page.setCurrentPageNo(_currentPageNo);
        Message message = sysLoginLogService.queryList(entry, page);
        return message;
    }

    @Log("登录日志根据主键查询")
//    @SaCheckPermission("system:sysLoginLog:get")
    @GetMapping("/sysLoginLog/queryById/{id}")
    public Message queryById(@PathVariable("id") Integer id) {
        SysLoginLogVo sysLoginLogVo = sysLoginLogService.queryById(id);
        return Message.success(sysLoginLogVo);
    }

//    @Log("登录日志导出")
////    @SaCheckPermission("system:sysLoginLog:export")
//    @GetMapping("/exportExcel")
//    public void exportExcel(SysLoginLogVo entity) {
//        List<SysLoginLogVo> list = sysLoginLogService.queryList(getAllPage(), entity).getRecords();
//        EasyPoiExcelUtils.exportExcel(list, "登录日志-" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss"), SysLoginLogVo.class);
//    }

    @Log("登录日志根据主键删除")
//    @SaCheckPermission("system:sysLoginLog:remove")
    @GetMapping("/sysLoginLog/removeById/{id}")
    public Message removeById(@PathVariable("id") Integer id) {
        boolean b = sysLoginLogService.removeById(id);
        if (b){
            return Message.success(b);
        }
        return Message.error("删除失败");
    }

    @Log("登录日志根据主键批量删除")
//    @SaCheckPermission("system:sysLoginLog:removeBatch")
    @RequestMapping("/sysLoginLog/removeBatchByIds")
    public Message removeBatchByIds(@RequestBody Integer[] ids) {
        boolean b = sysLoginLogService.removeByIds(Arrays.asList(ids));
        if (b){
            return Message.success(b);
        }
        return Message.error("删除失败");
    }
}
