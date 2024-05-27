package com.kgc.entity;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelFiled("年")
    private String orderYear;
    @ExcelFiled("月")
    private String orderMonth;
    @ExcelFiled("总数量")
    private int orderCount;//总数量
    @ExcelFiled("总金额")
    private BigDecimal totalAmount;//总金额

    private int currentPage;

    private String name;//名字
    private String categoryName;
    private String unitName;
    private String specification;
    private int totalQuantity;



}
