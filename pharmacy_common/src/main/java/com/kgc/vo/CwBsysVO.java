package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 报损应收VO
 */
@Data
public class CwBsysVO implements Serializable {

    /**
     * 报损应收id
     */
    @ExcelFiled("报损应收id")
    private Integer id;
    /**
     * 报损应收编码
     */
    @ExcelFiled("报损应收编码")
    private String code;
    /**
     * 原单号
     */
    @ExcelFiled("原单号")
    private String originalOrder;
    /**
     * 金额
     */
    @ExcelFiled("金额")
    private BigDecimal cost;
    /**
     * 创建时间
     */
    @ExcelFiled("创建时间")
    private LocalDateTime createTime;


    private Integer storehouseId;
    @ExcelFiled("仓库")
    private String storehouseName;

    private Integer reportedTypeId;
    @ExcelFiled("报损原因")
    private String reportedTypeName;
    @ExcelFiled("审批人")
    private String approverByName;

    private String createBy;
    @ExcelFiled("报损人")
    private String createByName;
}
