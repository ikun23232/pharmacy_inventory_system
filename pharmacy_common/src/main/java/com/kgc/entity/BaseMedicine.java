package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private Integer categoryId;

    /**
     * 计量单位
     */
    @TableField("unitId")
    private Integer unitId;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
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
    private Integer warning;

    @TableField(exist=false)
    @ExcelFiled("医药品分类名")
    private String categoryName;

    @TableField(exist=false)
    @ExcelFiled("计量单位")
    private String unitName;

    @TableField(exist=false)
    private Integer currentPage;

    @TableField(exist=false)
    @ExcelFiled("数量")
    private Integer quantity;

    @TableField(exist=false)
    @ExcelFiled("创建人名称")
    private String createByName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ExcelFiled("创造时间")
    private Date createTime;

    @TableField(exist=false)
    @ExcelFiled("修改人名称")
    private String updateByName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ExcelFiled("修改时间")
    private Date updateTime;

    private Integer createBy;
    private Integer updateBy;

    @TableField(exist=false)
    private BigDecimal totalPrice;

    @TableField(exist=false)
    private Integer providerId;

    @TableField(exist=false)
    @ExcelFiled("供应商名称")
    private String providerName;

    @TableField(exist=false)
    @ExcelFiled("采购价")
    private BigDecimal purchasePrice;

    @TableField(exist=false)
    private String code;//单据编号

    /**
     * 原单号
     */

    private String  sourceCode;

    /**
     * 订单药品表id
     */
    private Integer medicineOrderId;
    private Integer medicineId;
    private Integer stock;

    @TableField(exist=false)
    private String batchCode;


    @TableField(exist=false)
    private String storehouseName;

    private Integer storeHouseId;

    private Integer aimStoreHouseId;

    private Integer kcMedicineId;//库存明细id

    private Integer totalWarning;//全部仓库预警值
    @TableField(exist=false)
    private Integer fowardWarHouseId;
    @TableField(exist=false)
    private String fowardWarHouseName;

    @TableField(exist=false)
    private String orderDateBegin;
    @TableField(exist=false)
    private String orderDateEnd;

}