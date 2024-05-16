package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CgPayNum {
    //查询当年支付订单数据

    //年份
    private int year;
    //月份
    private int month;
    //所有订单数量
    private int allPayCount;
    //支付订单数
    private int paidCount;
    //未支付订单数
    private int unpaidCount;
    //支付金额
    private BigDecimal totalPaidAmount;
    //未支付金额
    private BigDecimal totalUnpaidAmount;
}
