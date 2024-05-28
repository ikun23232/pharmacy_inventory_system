package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcDispatchdetails;

import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/20 14:45
 */
public interface KcDispatchDetailMapper extends BaseMapper<KcDispatchdetails> {
    public List<KcDispatchdetails> getKcDetailsList(String dispatchCode);
    public int deleteDispatchByCode(String dispatchCode);
}
