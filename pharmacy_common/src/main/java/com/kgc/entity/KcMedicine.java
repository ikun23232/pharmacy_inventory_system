package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class KcMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存-药品主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * 仓库id
     */
    @TableField("storehouseId")
    private Integer storehouseId;

    /**
     * 仓库编号
     */
    private String code;

    /**
     * 批次编号
     */
    @TableField("batchCode")
    private String batchCode;

    /**
     * 药品id
     */
    @TableField("medicineId")
    private Integer medicineId;

    /**
     * 供应商id
     */
    @TableField("providerId")
    private Integer providerId;

    /**
     * 数量
     */
    private int quantity;

    /**
     * 库存价值
     */
    private BigDecimal money;

    /**
     * 药品名称
     */
    @TableField("medicineName")
    private String medicineName;

    /**
     * 供应商名称
     */
    @TableField("providerName")
    private String providerName;

    private int reportedNum;

}
