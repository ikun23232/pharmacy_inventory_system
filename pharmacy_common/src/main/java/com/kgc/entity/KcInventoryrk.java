package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kc_inventoryrk")
public class KcInventoryrk implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入库id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 盘点详细id外键

     */
    private Integer inventorydetailId;

    /**
     * 盘点入库编号
     */
    private String inventoryCode;

    /**
     * 药品id外键
     */
    private Integer medicineId;

    /**
     * 入库数量
     */
    private Integer rkQuantity;

    /**
     * 删除标识
     */
    @TableLogic
    private Integer isdel;


    @TableField(exist = false)
    private String code;
    @TableField(exist = false)
    private String materialCategoryName;
    @TableField(exist = false)
    private String storehouseName;
    @TableField(exist = false)
    private String providerName;
    @TableField(exist = false)
    private String startTime;
    @TableField(exist = false)
    private String endTime;
    @TableField(exist = false)
    private String current;

    private Integer storehouseId;
    private Integer providerId;
    private Date createDate;
    private Integer isApproved;

}
