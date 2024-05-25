package com.kgc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelIgnoreUnannotated
public class KcOutintodetial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 明细id
     */
      @TableId(value = "id", type = IdType.AUTO)
      @ExcelIgnore
    private Integer id;

    /**
     * 明细编号
     */
    @ExcelProperty("明细编号")
    private String code;

    /**
     * 进出库类型
     */
    @TableField("typeId")
    @ExcelIgnore

    private Integer typeid;

    /**
     * 创造时间
     */
    @TableField("createDate")
    @ExcelProperty("执行时间")
    private Date createdate;

    /**
     * 创造人
     */
    @TableField("createBy")
    @ExcelIgnore
    private Integer createby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    @ExcelIgnore
    private Date updatedate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    @ExcelIgnore
    private Integer updateby;

    /**
     * 删除id
     */
    @TableLogic
    @TableField("isDel")
    @ExcelIgnore
    private String isdel;

    /**
     * 订单编号
     */
    @TableField("orderCode")
    @ExcelIgnore
    private String ordercode;

    @ExcelIgnore
    private Integer medicineid;
    @TableField(exist = false)
    @ExcelProperty("药品名称")
    private String medicineName;
    /**
     * 供应商Id
     */
    @ExcelIgnore

    private Integer providerid;

    @TableField(exist = false)
    @ExcelProperty("供应商名称")
    private String providerName;
    /**
     * 入库金额
     */
    @ExcelProperty("入库金额")
    private BigDecimal tostockmoney;
    /**
     * 批次编号
//     */
//    private String batchCode;
    /**
     * 入库数量
     */
    @ExcelProperty("入库数量")
    private Integer tostockquantity;
    /**
     * 出库金额
     */
    @ExcelProperty("出库金额")
    private BigDecimal fromstockmoney;
    /**
     * 出库数量
     */
    @ExcelProperty("出库数量")
    private Integer fromstockquantity;
    /**
     * 单价(当前)
     */
    @ExcelProperty("单价")
    private BigDecimal price;
    /**
     * 删除id
    private String isdel;

     * 进出库仓库
     */
    @ExcelIgnore
    private Integer warehouseid;
    @TableField(exist = false)
    @ExcelProperty("出/入仓库")
    private String warehouseName;
    @TableField(exist = false)
    @ExcelProperty("商品规格")
    private String specification;
    @TableField(exist = false)
    @ExcelProperty("计量名称")
    private String unitName;
    @TableField(exist = false)
    @ExcelProperty("类型")
    private String type;
    @ExcelProperty("批次号")
    private String batchCode;






}
