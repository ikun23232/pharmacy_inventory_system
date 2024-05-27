package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * 采购应付VO
 */
@Data
public class CwCgyfVO implements Serializable {
    /**
     * 采购应付id
     */
    @ExcelFiled("采购应付id")
    private Integer id;
    /**
     * 采购应付编号
     */
    @ExcelFiled("采购应付编号")
    private String code;
    /**
     * 采购订单编号
     */
    @ExcelFiled("采购订单编号")
    private String cgddCode;
    /**
     * 供应商id
     */

    private Integer providerId;
    /**
     * 供应商名称
     */
    @ExcelFiled("供应商名称")
    private String providerName;
    /**
     * 采购应付状态(付款状态(0未知,1待付款,2已付款))
     */
    @ExcelFiled("采购应付状态(1待付款,2已付款)")
    private Integer isPay;
    /**
     * 采购应付金额
     */
    @ExcelFiled("采购应付金额")
    private BigDecimal cost;
    /**
     * 采购应付生成时间
     */
    @ExcelFiled("采购应付生成时间")
    private LocalDateTime createTime;
    /**
     * 付款时间
     */
    @ExcelFiled("付款时间")
    private LocalDateTime paymentTime;


}
