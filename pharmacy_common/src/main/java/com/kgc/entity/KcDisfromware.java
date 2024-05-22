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
import java.util.Date;
import java.util.List;

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
public class KcDisfromware implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度出库id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    /**
     * 调度出库单据
     */
    @ExcelProperty("调度出库编号")
    private String code;

    /**
     * 调度单
     */
    @TableField("dispatchId")
    @ExcelIgnore
    private Integer dispatchid;

    /**
     * 删除id
     */
    @TableLogic
    @TableField("isDel")
    @ExcelIgnore
    private Integer isdel;

    @TableField(exist = false)
    @ExcelProperty("调度源单号")
    private String dispatchCode;

    @TableField(exist = false)
    @ExcelProperty("主题")
    private String subject;

    @TableField(exist = false)
    @ExcelProperty("调度时间")
    private Date dispatchTime;

    @TableField(exist = false)
    @ExcelProperty("调度金额")
    private BigDecimal price;

    @TableField(exist = false)
    @ExcelIgnore
    private Integer beforeWarehouseId;

    @TableField(exist = false)
    @ExcelProperty("源仓库")
    private String beforeWarehouseName;
    @TableField(exist = false)
    @ExcelIgnore
    private Integer aimStoreHouseId;

    @TableField(exist = false)
    @ExcelIgnore
    private String aimStoreHouseName;
    @TableField(exist = false)
    @ExcelProperty("备注")
    private String remark;

    @TableField(exist = false)
    @ExcelIgnore
    private Integer orderStatus;

    @TableField(exist = false)
    @ExcelIgnore
    private Integer approverBy;
    @TableField(exist = false)
    @ExcelIgnore
    private Integer approverRemark;
    @TableField(exist = false)
    @ExcelIgnore
    private Integer documenterBy;
    @TableField(exist = false)
    @ExcelIgnore
    private String documenterName;

    @TableField(exist = false)
    @ExcelIgnore
    private Integer voidState;
    @TableField(exist = false)
    private Date createDate;
    @ExcelIgnore
    @TableField(exist = false)
    private Date updateDate;
    @ExcelIgnore
    private List<KcDispatchdetails> medicineList;


















}
