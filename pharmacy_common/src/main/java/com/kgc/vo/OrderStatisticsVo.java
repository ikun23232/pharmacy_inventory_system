package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderStatisticsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelFiled("年")
    private String orderYear;
    @ExcelFiled("月")
    private String orderMonth;
    @ExcelFiled("医用商品名称")
    private String name;//名字
    @ExcelFiled("医用商品分类")
    private String categoryName;
    @ExcelFiled("计量单位")
    private String unitName;
    @ExcelFiled("型号规格")
    private String specification;
    @ExcelFiled("总数量")
    private int totalQuantity;
    @ExcelFiled("总金额")
    private BigDecimal totalAmount;//总金额
}
