package com.kgc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
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
    @ExcelFiled("医用商品名称")
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
     * 医用商品规格
     */
    @ExcelFiled("医用商品规格")
    private String specification;
    /**
     * 零售价
     */
    @ExcelFiled("零售价")
    private BigDecimal salePrice;

    /**
     * 库存预警值
     */
    @ExcelIgnore
    private Integer warning;

    @TableField(exist=false)
    @ExcelFiled("医药品分类名")
    private String categoryName;
    @TableField(exist=false)
    @ExcelFiled("计量单位")
    private String unitName;

    @TableField(exist=false)
    @ExcelFiled("数量")
    private Integer quantity;

    @TableField(exist=false)
    @ExcelFiled("创建人名称")
    private String createByName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ExcelFiled("创造时间")
    private Date createTime;

    @TableField(exist=false)
    @ExcelFiled("修改人名称")
    private String updateByName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ExcelFiled("修改时间")
    private Date updateTime;

    @ExcelIgnore
    private Integer createBy;
    @ExcelIgnore
    private Integer updateBy;




    @TableField(exist=false)
    @ExcelIgnore
    private Integer currentPage;

    @TableField(exist=false)
    @ExcelIgnore
    private BigDecimal totalPrice;

    @TableField(exist=false)
    @ExcelIgnore
    private Integer providerId;

    @TableField(exist=false)
    @ExcelFiled("供应商名称")
    private String providerName;

    @TableField(exist=false)
    @ExcelFiled("采购价")
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
    private Integer stock;

    @TableField(exist=false)
    @ExcelIgnore
    private String batchCode;


    @TableField(exist=false)
    @ExcelIgnore
    private String storehouseName;
    @ExcelIgnore
    private Integer storeHouseId;

    @ExcelIgnore
    private Integer aimStoreHouseId;

    @ExcelIgnore
    private Integer kcMedicineId;//库存明细id

    private Integer totalWarning;//全部仓库预警值
}
