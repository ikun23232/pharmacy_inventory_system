package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.CwXstk;
import com.kgc.entity.CwXsys;
import com.kgc.vo.CwXstkVO;
import com.kgc.vo.CwXsysVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CwXstkDao extends BaseMapper<CwXstk> {

    List<CwXstk> getXstkList(CwXstk cwXstk);

    CwXstk getXstkByCode(@Param("code")String code);

    List<CwXsys> getXsysList(CwXsys cwXsys);

    CwXsys getXsysByCode(@Param("code")String code);

    List<CwXstkVO> getXstkVOList();

    List<CwXsysVO> getXsysVOList();

}
