package com.kgc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/20 14:39
 */
@Data
public class KcDispatchdetails implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("aimStoreHouseId")
    private Integer aimStoreHouseId;
    @ExcelFiled("目的仓库")
    private String aimStoreHouseName;

    @TableField("medicineId")
    private Integer medicineId;
    @ExcelFiled("药品名称")
    private String medicineName;
    @ExcelFiled("分类名称")
    private String categoryName;

    @TableField("quantity")
    @ExcelFiled("数量")
    private Integer quantity;

    @TableField(exist = false)
    @ExcelFiled("采购价")
    private BigDecimal purchasePrice;//采购价

    @TableField(exist = false)
    @ExcelFiled("剩余库存")
    private Integer stock;//剩余库存

    @TableField("price")
    @ExcelFiled("库存总价")
    private BigDecimal price;

    @TableField("batchCode")
    @ExcelFiled("批次号")
    private String batchCode;

    @TableField("isDel")
    private Integer isDel;

    @TableField("dispatchCode")
    @ExcelFiled("源单号")
    private String dispatchCode;

    @TableField(exist = false)
    private Integer unitId;

    @TableField(exist = false)
    @ExcelFiled("计量单位")

    private String unitName;

    @TableField(exist = false)
    @ExcelFiled("药品名称")
    private String name;

    @TableField(exist = false)
    @ExcelFiled("药品规格")
    private String specification;

    @TableField("providerId")
    private Integer providerId;
    @ExcelFiled("供应商名称")
    private String providerName;
}
