package com.kgc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class CwNumByYear {

    //年份
    private String year;
    //月份
    private String month;
    //入账数量
    private int inCount;
    //入账金额
    private BigDecimal totalInAmount;
    //出账数量
    private int outCount;
    //出账金额
    private BigDecimal totalOutAmount;

}
