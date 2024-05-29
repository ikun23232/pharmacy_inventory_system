package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/27 15:47
 */
@Data
public class DispatchMedicineVO {
    private Integer id;//映射id
    @ExcelFiled("医用商品名称")
    private String name;
    @ExcelFiled("医用商品规格")
    private String specification;
    @ExcelFiled("医药品分类名")
    private String categoryName;
    @ExcelFiled("计量单位")
    private String unitName;
    @ExcelFiled("调度数量")
    private Integer quantity;
    @ExcelFiled("进价")
    private BigDecimal purchasePrice;
}
