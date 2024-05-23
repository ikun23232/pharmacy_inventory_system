package com.kgc.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderYear;
    private String orderMonth;
    private int orderCount;//总数量
    private BigDecimal totalAmount;//总金额

    private int currentPage;

    private String name;//名字
    private String categoryName;
    private String unitName;
    private String specification;
    private int totalQuantity;



}
