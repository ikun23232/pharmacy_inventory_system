package com.kgc.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kgc.entity.SysLogManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.vo.SysOperationLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Mapper
public interface SysLogManageMapper extends BaseMapper<SysLogManage> {
    /**
     * 查询列表
     *
     * @param page
     * @param entity
     * @return
     */
    List<SysOperationLogVo> queryList(@Param("username") String username,@Param("ip") String ip,@Param("title") String title,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    SysOperationLogVo queryById(@Param("id") Integer id);
}
