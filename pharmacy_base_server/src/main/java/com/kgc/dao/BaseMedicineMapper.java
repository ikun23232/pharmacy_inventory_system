package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseMedicine;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
public interface BaseMedicineMapper extends BaseMapper<BaseMedicine> {

    /**
     * 分页查询药品
     * @return
     */
    public List<BaseMedicine> getBaseMedicineList();



}
