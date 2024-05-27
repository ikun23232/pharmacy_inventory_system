package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/24 8:46
 */
@Data
public class MedicineVO implements Serializable {
    @ExcelFiled("医用商品名称")
    private String name;
    @ExcelFiled("零售价")
    private BigDecimal salePrice;
    @ExcelFiled("医用商品规格")
    private String specification;
    @ExcelFiled("创造时间")
    private Date createTime;
    @ExcelFiled("修改时间")
    private Date updateTime;
    @ExcelFiled("创造人名称")
    private String createByName;
    @ExcelFiled("修改人名称")
    private String updateByName;
    @ExcelFiled("医药品分类名")
    private String categoryName;
    @ExcelFiled("计量单位")
    private String unitName;
    @ExcelFiled("数量")
    private Integer quantity;
    @ExcelFiled("供应商名称")
    private String providerName;
    @ExcelFiled("采购价")
    private BigDecimal purchasePrice;
}
