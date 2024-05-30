package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kgc.annotation.ExcelFiled;
import com.kgc.entity.BaseMedicine;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售退款VO
 **/
@Data
public class CwXstkVO {
    /**
     * 销售退款id
     */
    @ExcelFiled("销售退款id")
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
    private LocalDateTime createTime;
    /**
     * 原单号
     */
    @ExcelFiled("原单号")
    private String originalOrder;
    /**
     * 退货原因
     */
    @ExcelFiled("退货原因")
    private String backReson;
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
     * 销售员名称(外表字段)
     */
    @ExcelFiled("销售员名称")
    private String createName;
    /**
     * 银行账户(外表字段)
     */
    @ExcelFiled("银行账户")
    private String bandCount;
    /**
     * 退款类型(外表字段)
     */
    @ExcelFiled("退款类型")
    private String refundType;
    /**
     * 审核意见(外表字段)
     */
    @ExcelFiled("审核意见")
    private String opinion;


    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    private List<BaseMedicine> baseMedicineList;
}
