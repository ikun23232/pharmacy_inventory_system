package com.kgc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
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
public class KcDisfromware implements Serializable {

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
    @ExcelFiled("源仓库")
    private String beforeWarehouseName;
    @TableField(exist = false)
    private Integer aimStoreHouseId;
    @TableField(exist = false)
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
    @ExcelFiled(value = "药品明细",type = KcDispatchdetails.class)
    private List<KcDispatchdetails> medicineList;

}
