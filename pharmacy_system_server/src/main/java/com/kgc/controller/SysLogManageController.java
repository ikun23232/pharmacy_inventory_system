package com.kgc.controller;


import com.kgc.annotation.Log;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysUser;
import com.kgc.service.SysLogManageService;
import com.kgc.vo.SysOperationLogVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysLogManageController {
    private final SysLogManageService sysOperationLogService;

//    @SaCheckPermission("system:sysOperationLog:list")
    @RequestMapping("/sysLogManage/list")
    public Message queryList(@RequestBody SysOperationLogVo entity) {
        int _currentPageNo = 1;
        if (entity.getCurrent() != null && !"".equals(entity.getCurrent())) {
            _currentPageNo = Integer.parseInt(entity.getCurrent());
        }
        Page page = new Page();
        page.setPageSize(4);
        page.setCurrentPageNo(_currentPageNo);
        Message message = sysOperationLogService.queryList(entity, page);
        return message;
    }

    @Log("操作日志根据主键查询")
//    @SaCheckPermission("system:sysOperationLog:get")
    @GetMapping("/sysLogManage/queryById/{id}")
    public Message queryById(@PathVariable("id") Integer id) {
        SysOperationLogVo sysOperationLogVo = sysOperationLogService.queryById(id);
        return Message.success(sysOperationLogVo);
    }

//    @ApiOperation("操作日志导出")
//    @Log("操作日志导出")
//    @SaCheckPermission("system:sysOperationLog:export")
//    @GetMapping("/exportExcel")
//    public void exportExcel(SysOperationLogVo entity) {
//        List<SysOperationLogVo> list = sysOperationLogService.queryList(getAllPage(), entity).getRecords();
//        EasyPoiExcelUtils.exportExcel(list, "操作日志-" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss"), SysOperationLogVo.class);
//    }

    @Log("操作日志根据主键删除")
//    @SaCheckPermission("system:sysOperationLog:remove")
    @GetMapping("/sysLogManage/removeById/{id}")
    public Message removeById(@PathVariable("id") Integer id) {
        boolean b = sysOperationLogService.removeById(id);
        if (b){
            return Message.success(b);
        }
        return Message.error("删除失败");
    }

    @Log("操作日志根据主键批量删除")
//    @SaCheckPermission("system:sysOperationLog:removeBatch")
    @RequestMapping("/sysLogManage/removeBatchByIds")
    public Message removeBatchByIds(@RequestBody Integer[] ids) {
        boolean b = sysOperationLogService.removeByIds(Arrays.asList(ids));
        if (b){
            return Message.success(b);
        }
        return Message.error("删除失败");
    }

    @Log("操作日志导出")
//    @SaCheckPermission("system:sysOperationLog:removeBatch")
    @RequestMapping("/sysLogManage/LogManageexcel")
    public void LogManageexcel(@RequestBody SysOperationLogVo sysOperationLogVo , HttpServletResponse response) {
       sysOperationLogService.LogManageexcel(sysOperationLogVo,response);

    }
}

