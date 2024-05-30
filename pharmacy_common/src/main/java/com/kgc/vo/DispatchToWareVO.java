package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import com.kgc.entity.KcDispatchdetails;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/30 15:54
 */
@Data
public class DispatchToWareVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 调度出库id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 调度出库单据
     */
    @ExcelFiled("调度出库编号")
    private String code;

    /**
     * 调度单
     */
    @TableField("dispatchId")
    private Integer dispatchid;

    /**
     * 删除id
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;

    @TableField(exist = false)
    @ExcelFiled("调度源单号")
    private String dispatchCode;

    @TableField(exist = false)
    @ExcelFiled("主题")
    private String subject;

    @TableField(exist = false)
    @ExcelFiled("调度时间")
    private Date dispatchTime;

    @TableField(exist = false)
    @ExcelFiled("调度金额")
    private BigDecimal price;
    @ExcelFiled("调度数量")
    private Integer totalCount;
    @TableField(exist = false)
    private Integer beforeWarehouseId;

    @TableField(exist = false)
    private String beforeWarehouseName;
    @TableField(exist = false)
    private Integer aimStoreHouseId;

    @TableField(exist = false)
    @ExcelFiled("调入目标仓库")
    private String aimStoreHouseName;
    @TableField(exist = false)
    @ExcelFiled("备注")
    private String remark;

    @TableField(exist = false)
    private Integer orderStatus;

    @TableField(exist = false)
    private Integer approverBy;
    @TableField(exist = false)
    private Integer approverRemark;
    @TableField(exist = false)
    private Integer documenterBy;
    @TableField(exist = false)
    private String documenterName;
    @TableField(exist = false)
    private Integer voidState;
    @TableField(exist = false)
    private Date createDate;
    @TableField(exist = false)
    private Date updateDate;


    @TableField("medicineId")
    private Integer medicineId;
    private String medicineName;
    @ExcelFiled("分类名称")
    private String categoryName;

    @TableField("quantity")
    private Integer quantity;

    @TableField("dispatchPrice")
    private BigDecimal dispatchPrice;

    @TableField(exist = false)
    @ExcelFiled("采购价")
    private BigDecimal purchasePrice;//采购价

    @TableField("batchCode")
    @ExcelFiled("批次号")
    private String batchCode;

    @TableField("isDel")
    private Integer isDel;

    @TableField(exist = false)
    private Integer unitId;

    @TableField(exist = false)
    @ExcelFiled("计量单位")

    private String unitName;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    @ExcelFiled("药品规格")
    private String specification;

    @TableField("providerId")
    private Integer providerId;
    @ExcelFiled("供应商名称")
    private String providerName;
}
