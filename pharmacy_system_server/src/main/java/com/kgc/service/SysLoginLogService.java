package com.kgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysLoginLog;
import com.kgc.vo.SysLoginLogVo;
import com.kgc.vo.SysOperationLogVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录日志 接口
 *
 * @author Tellsea
 * @date 2022-12-30
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    /**
     * 查询列表
     *
     * @param page
     * @param entity
     * @return
     */
    Message queryList(SysLoginLogVo entry, Page page);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    SysLoginLogVo queryById(Integer id);
    public void LogLoginexcel(SysLoginLogVo sysLoginLogVo, HttpServletResponse response);
}
