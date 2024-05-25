package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CgPayCom {
    //采购订单支付情况

    //支付状态
    private String paymentStatus;
    //订单总数
    private int orderCount;
    //总金额
    private BigDecimal totalAmount;

}
