package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.SysLogManage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kgc.vo.SysOperationLogVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface SysLogManageService extends IService<SysLogManage> {
    /**
     * 查询列表
     *
     * @param page
     * @param entity
     * @return
     */
    Message queryList(SysOperationLogVo entry, Page page);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    SysOperationLogVo queryById(Integer id);
}
