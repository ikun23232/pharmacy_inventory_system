package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.XsOrder;

import java.util.List;

public interface SaleOrderMapper extends BaseMapper<XsOrder> {

    /**
     * 销售订单分页
     * @param xsOrder
     * @return
     */
    public List<XsOrder> getSaleOrderListByPage(XsOrder xsOrder);


}
