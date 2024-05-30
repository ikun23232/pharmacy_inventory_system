package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.XsOrder;
import com.kgc.vo.RefundOrderVo;

import java.util.List;

public interface RefundOrderMapper extends BaseMapper<XsOrder> {

    /**
     * 退货订单分页
     * @param xsOrder
     * @return
     */
    public List<XsOrder> getRefundOrderListByPage(XsOrder xsOrder);

    public List<RefundOrderVo> getRefundOrderList(XsOrder xsOrder);
}
