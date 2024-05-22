package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kgc.entity.SysLoginLog;
import com.kgc.vo.SysLoginLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录日志 Mapper 接口
 *
 * @author Tellsea
 * @date 2022-12-30
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    /**
     * 查询列表
     *

     * @return
     */
    List<SysLoginLogVo> queryList(@Param("userName") String userName,@Param("operationIp") String operationIp,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    SysLoginLogVo queryById(@Param("id") Integer id);
}
