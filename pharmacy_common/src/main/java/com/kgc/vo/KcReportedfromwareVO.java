package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class KcReportedfromwareVO implements Serializable {
    /**
     * 报损出库id
     */
    @ExcelFiled("报损出库id")
    private Integer id;
    /**
     * 报损出库编号
     */
    @ExcelFiled("报损出库编号")
    private String code;
    /**
     * 报损id
     */
    private Integer reportedId;
    /**
     * 报损原单号(外表字段)
     */
    @ExcelFiled("报损原单号")
    private String reportedCode;
    /**
     * 仓库id(外表字段)
     */
    private Integer storehouseId;
    /**
     * 仓库名称(外表字段)
     */
    @ExcelFiled("仓库名称")
    private String storehouseName;
    /**
     * 报损类型id(外表字段)
     */
    private Integer reportedTypeId;
    /**
     * 报损类型(外表字段)
     */
    @ExcelFiled("报损类型")
    private String reportedTypeName;
    /**
     * 出库时间(外表字段)
     */
    @ExcelFiled("出库时间")
    private LocalDateTime modificationTime;
    /**
     * 出库人(外表字段)
     */
    @ExcelFiled("出库人")
    private String modificationName;
    /**
     * 制单人(外表字段)
     */
    @ExcelFiled("制单人")
    private String documenterName;
    /**
     * 制单时间(外表字段)
     */
    @ExcelFiled("制单时间")
    private LocalDateTime createTime;
}
