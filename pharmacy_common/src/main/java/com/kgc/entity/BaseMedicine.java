package com.kgc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class BaseMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    /**
     * 医用商品名称
     */
    @ExcelProperty("医用商品名称")
    private String name;

    /**
     * 医用商品类型
     */
    @TableField("categoryId")
    @ExcelIgnore
    private Integer categoryId;

    /**
     * 计量单位
     */
    @TableField("unitId")
    @ExcelIgnore
    private Integer unitId;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
    @ExcelIgnore
    private Integer isDel;

    /**
     * 零售价
     */
    @ExcelProperty("零售价")
    private BigDecimal salePrice;

    /**
     * 医用商品规格
     */
    @ExcelProperty("医用商品规格")
    private String specification;

    /**
     * 库存预警值
     */
    @ExcelIgnore
    private Integer warning;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ExcelProperty("创造时间")
    private Date createTime;

    @ExcelIgnore
    private Integer createBy;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ExcelProperty("修改时间")
    private Date updateTime;

    @ExcelIgnore
    private Integer updateBy;

    @TableField(exist=false)
    @ExcelProperty("创造人名称")
    private String createByName;

    @TableField(exist=false)
    @ExcelProperty("修改人名称")
    private String updateByName;

    @TableField(exist=false)
    @ExcelProperty("医药品分类名")
    private String categoryName;

    @TableField(exist=false)
    @ExcelProperty("计量单位")
    private String unitName;

    @TableField(exist=false)
    @ExcelIgnore
    private Integer currentPage;

    @TableField(exist=false)
    @ExcelProperty("数量")
    private Integer quantity;

    @TableField(exist=false)
    @ExcelIgnore
    private BigDecimal totalPrice;

    @TableField(exist=false)
    @ExcelIgnore
    private Integer providerId;

    @TableField(exist=false)
    @ExcelProperty("供应商名称")
    private String providerName;

    @TableField(exist=false)
    @ExcelProperty("采购价")
    private BigDecimal purchasePrice;

    @TableField(exist=false)
    @ExcelIgnore
    private String code;//单据编号

    /**
     * 原单号
     */
    @ExcelIgnore
    private String  sourceCode;

    /**
     * 订单药品表id
     */
    @ExcelIgnore
    private Integer medicineOrderId;
    @ExcelIgnore
    private Integer medicineId;
    @ExcelIgnore
    private int stock;

    @TableField(exist=false)
    @ExcelIgnore
    private String batchCode;


    @TableField(exist=false)
    @ExcelIgnore
    private String storehouseName;
    @ExcelIgnore
    private int storeHouseId;

    @ExcelIgnore
    private int aimStoreHouseId;

    @ExcelIgnore
    private int kcMedicineId;//库存明细id
}
