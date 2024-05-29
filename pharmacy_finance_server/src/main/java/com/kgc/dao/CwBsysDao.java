package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseStorehouse;
import com.kgc.entity.CwBsys;
import com.kgc.vo.CwBsysVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CwBsysDao extends BaseMapper<CwBsys> {

    List<CwBsys> getCwbsysList(CwBsys cwBsys);

    CwBsys getCwbsysByCode(@Param("code") String code);
    List<BaseStorehouse> getStorehouseList();

    List<CwBsysVO> getCwbsysVOList();
}
