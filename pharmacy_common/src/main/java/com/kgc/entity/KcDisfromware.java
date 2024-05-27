package com.kgc.entity;

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
    private String dispatchCode;

    @TableField(exist = false)
    private String subject;

    @TableField(exist = false)
    private Date dispatchTime;

    @TableField(exist = false)
    private BigDecimal price;

    @TableField(exist = false)
    private Integer beforeWarehouseId;

    @TableField(exist = false)
    private String beforeWarehouseName;
    @TableField(exist = false)
    private Integer aimStoreHouseId;

    @TableField(exist = false)
    private String aimStoreHouseName;
    @TableField(exist = false)
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
    private List<KcDispatchdetails> medicineList;


















}
