package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import com.kgc.entity.BaseMedicine;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售应收VO
 **/
@Data
public class CwXsysVO {
    /**
     * 销售应收id
     */
    @ExcelFiled("销售应收id")
    private Integer id;
    /**
     * 单据编号
     */
    @ExcelFiled("单据编号")
    private String code;
    /**
     * 创建时间
     */
    @ExcelFiled("创建时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 原单号
     */
    @ExcelFiled("原单号")
    private String originalOrder;
    /**
     * 药品名
     */
    @ExcelFiled("药品名")
    private String medicine;
    /**
     * 金额
     */
    @ExcelFiled("金额")
    private BigDecimal cost;
    /**
     * 是否删除
     */
    private String isDel;
    /**
     * 销售员
     */
    private String createBy;
    /**
     * 销售员名称
     */
    @ExcelFiled("销售员名称")
    private String createName;
    /**
     * 银行账户(外表字段)
     */
    @ExcelFiled("银行账户")
    private String bandCount;
    /**
     * 审核意见(外表字段)
     */
    @ExcelFiled("审核意见")
    private String opinion;

    @TableField(exist=false)
    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    private List<BaseMedicine> baseMedicineList;
}
