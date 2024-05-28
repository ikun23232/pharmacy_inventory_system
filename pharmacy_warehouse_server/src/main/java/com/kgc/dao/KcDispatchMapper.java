package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcDispatch;
import com.kgc.vo.DispatchExcelVO;
import com.kgc.vo.DispatchVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/14 14:11
 */
public interface KcDispatchMapper extends BaseMapper<KcDispatch> {
    public List<KcDispatch> getKcDispathList(DispatchVO dispatchVO);
    @Override
    KcDispatch selectById(Serializable id);
    List<DispatchExcelVO> excelDispatch(DispatchVO dispatchVO);
}
