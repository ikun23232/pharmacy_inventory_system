package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 库存-药品报损详情vo
 */
@Data
public class KcMedicineBSVO implements Serializable {
    /**
     * 库存-药品主键id
     */
    @ExcelFiled("库存-药品主键id")
    private Integer id;
    /**
     * 批次编号
     */
    @ExcelFiled("批次编号")
    private String batchCode;
    /**
     * 仓库id
     */
    private Integer storehouseId;
    /**
     * 仓库名称(外表字段)
     */
    private String storehouseName;
    /**
     * 药品id
     */
    private Integer medicineId;
    /**
     * 药品名称(外表字段)
     */
    @ExcelFiled("药品名称")
    private String medicineName;
    /**
     * 供应商id
     */
    private Integer providerId;
    /**
     * 供应商名称(外表字段)
     */
    @ExcelFiled("供应商名称")
    private String providerName;
    /**
     * 库存数量
     */
    @ExcelFiled("库存数量")
    private Integer quantity;
    /**
     * 单价
     */
    @ExcelFiled("单价")
    private BigDecimal money;
    /**
     * 库存价值
     */
    @ExcelFiled("库存价值")
    private BigDecimal totalPrice;


    /**
     * 报损数量(外表字段)
     */
    @ExcelFiled("报损数量")
    private Integer reportedNum;

    /**
     * 报损明细id(外表字段)
     */
    private Integer reporteddetailId;

}
