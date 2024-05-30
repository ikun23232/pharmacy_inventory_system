package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.kgc.annotation.ExcelFiled;
import com.kgc.entity.BaseMedicine;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 发票VO
 */
@Data
public class CwInvoiceVO implements Serializable {
    /**
     * 发票id
     */
    @ExcelFiled("发票id")
    private Integer id;
    /**
     * 发票单据
     */
    @ExcelFiled("发票单据")
    private String code;
//    /**
//     * 发票编号
//     */
//    @ExcelFiled("发票编号")
//    private Integer InvoiceNumber;
    /**
     * 类型
     */
    private Integer categoryId;
    /**
     * 类型名称
     */
    @ExcelFiled("类型名称")
    private String categoryName;
    /**
     * 订单号
     */
    @ExcelFiled("订单号")
    private String orderNumber;
    /**
     * 创建人
     */
    private Integer createBy;
    /**
     * 创建人名称
     */
    @ExcelFiled("创建人名称")
    private String createName;
    /**
     * 创建时间
     */
    @ExcelFiled("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ExcelFiled("修改时间")
    private LocalDateTime modificationTime;
    /**
     * 修改人
     */
    private Integer modificationBy;
    /**
     * 修改人名称
     */
    @ExcelFiled("修改人名称")
    private String modificationName;
    /**
     * 发票总金额
     */
    @ExcelFiled("发票总金额")
    private BigDecimal cost;

    @TableField(exist=false)
    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    private List<BaseMedicine> baseMedicineList;

}
