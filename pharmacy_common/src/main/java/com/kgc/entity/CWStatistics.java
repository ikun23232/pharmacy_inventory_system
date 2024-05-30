package com.kgc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class CWStatistics {

    private int year;

    private int month;

    private BigDecimal totalIncome;

    private BigDecimal totalExpense;
}