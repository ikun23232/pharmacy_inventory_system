package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.KcSalefromware;
import com.kgc.vo.KcRefundInWareVo;
import com.kgc.vo.KcSalefromwareVo;

import java.util.List;

public interface RefundInWarehouseMapper extends BaseMapper<KcSalefromware> {

    public List<KcSalefromware> getRefundInWarehouseListByPage(KcSalefromware kcSalefromware);

    public List<KcRefundInWareVo> getRefundInWarehouseList(KcSalefromware kcSalefromware);

}
