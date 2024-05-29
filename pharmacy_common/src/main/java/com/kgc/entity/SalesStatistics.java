package com.kgc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer monthsSalesNum;

    private BigDecimal monthSalesPrice;

    private Integer weekSalesNum;

    private BigDecimal weekSalesPrice;

    private Integer daySalesNum;

    private BigDecimal daySalesPrice;
}
