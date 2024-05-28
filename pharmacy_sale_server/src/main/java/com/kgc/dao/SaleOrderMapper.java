package com.kgc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kgc.entity.BaseMedicine;
import com.kgc.entity.OrderMedicine;
import com.kgc.entity.XsOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleOrderMapper extends BaseMapper<XsOrder> {

    /**
     * 销售订单分页
     * @param xsOrder
     * @return
     */
    public List<XsOrder> getSaleOrderListByPage(XsOrder xsOrder);

    /**
     * 根据订单号查询销售订单
     * @param orderNo
     * @return
     */
    public XsOrder getSaleOrderByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据订单号查询订单详情
     * @param orderNo
     * @return
     */
    public List<BaseMedicine> getSaleOrderDetailByOrderNo(@Param("orderNo") String orderNo);

    //    public int addSaleOrder(XsOrder xsOrder);

    public int addSaleOrderDetail(OrderMedicine orderMedicine);

    /**
     * 修改销售订单
     * @param xsOrder
     * @return
     */
//    public int updateSaleOrder(XsOrder xsOrder);

    /**
     * 修改订单详情
     * @param orderMedicine
     * @return
     */
    public int updateSaleOrderDetail(OrderMedicine orderMedicine);

    /**
     * 删除订单详情
     * @param orderNo
     * @return
     */
    public int deleteOrderDetailByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 作废订单详情
     * @param orderNo
     * @return
     */
    public int cancelOrderDetailByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 物理删除订单
     * @param orderNo
     * @return
     */
    public int deleteSaleOrderByOrderNo(@Param("orderNo") String orderNo);

    public int deleteSaleOrderDetailByOrderNo(@Param("orderNo") String orderNo);
}
