package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MedicineStockVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelFiled("医用商品名称")
    private String name;
    @ExcelFiled("医用商品类型")
    private String categoryName;
    @ExcelFiled("医用商品规格")
    private String specification;
    @ExcelFiled("计量单位")
    private String unitName;
    @ExcelFiled("零售价")
    private BigDecimal salePrice;
    @ExcelFiled("仓库")
    private String storehouseName;
    @ExcelFiled("批次号")
    private String batchCode;
    @ExcelFiled("库存")
    private Integer stock;
    @ExcelFiled("库存预警值")
    private Integer warning;
    @ExcelFiled("创造人名称")
    private String createByName;
    @ExcelFiled("创造时间")
    private Date createTime;
    @ExcelFiled("修改人名称")
    private String updateByName;
    @ExcelFiled("修改时间")
    private Date updateTime;

}
