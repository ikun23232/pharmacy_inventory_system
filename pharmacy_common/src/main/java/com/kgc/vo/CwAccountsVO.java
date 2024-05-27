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
 * 财务流水VO
 */
@Data
public class CwAccountsVO implements Serializable {
    /**
     * 流水Id
     */
    @ExcelFiled("流水Id")
    private Integer id;
    /**
     * 流水编号
     */
    @ExcelFiled("流水编号")
    private String code;
    /**
     * 流水类别Id
     */
    private Integer categoryId;
    /**
     * 流水类别名称
     */
    @ExcelFiled("流水类别名称")
    private String accountsCategoryName;
    /**
     * 流水总价
     */
    @ExcelFiled("流水总价")
    private BigDecimal cost;
    /**
     * 订单Id
     */
    @ExcelFiled("订单Id")
    private String orderCode;
    /**
     * 创建时间
     */
    @ExcelFiled("创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ExcelFiled("修改时间")
    private LocalDateTime modificationTime;
    /**
     * 创建人
     */
    private Integer createBy;
    /**
     * 创建人姓名
     */
    @ExcelFiled("创建人姓名")
    private String createName;
    /**
     * 修改人
     */
    private Integer modificationBy;
    /**
     * 修改人姓名
     */
    @ExcelFiled("修改人姓名")
    private String modificationName;
    /**
     * 逻辑删除
     */
    private String isDel;
    /**
     * 银行卡Id
     */
    private Integer bankAcountId;
    /**
     * 银行卡号
     */
    @ExcelFiled("银行卡号")
    private String bandCount;
    /**
     * 描述
     */
    @ExcelFiled("描述")
    private String description;
}
