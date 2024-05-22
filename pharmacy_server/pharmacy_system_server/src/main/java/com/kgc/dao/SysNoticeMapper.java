package com.kgc.dao;

import com.kgc.entity.SysNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
public interface SysNoticeMapper extends BaseMapper<SysNotice> {
    public List<SysNotice> getNoticeList(@Param("noticetitle") String noticetitle,@Param("startTime") String startTime,@Param("endTime") String endTime);

    public SysNotice existNotice(@Param("noticetitle") String noticetitle,@Param("id") Integer id);
}
