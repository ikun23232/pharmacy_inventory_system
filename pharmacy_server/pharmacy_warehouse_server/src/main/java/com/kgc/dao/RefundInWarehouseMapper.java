package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcSalefromware;

import java.util.List;

public interface RefundInWarehouseMapper extends BaseMapper<KcSalefromware> {

    public List<KcSalefromware> getRefundInWarehouseListByPage(KcSalefromware kcSalefromware);

}
