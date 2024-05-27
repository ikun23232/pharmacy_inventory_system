package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * 库存药品(KcMedicine)实体类
 * 完整版(1.0)
 * @author lemon
 * @since 2024-05-20
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
     * 批次编号
     */
    @TableField("batchCode")
    private String batchCode;
    /**
     * 仓库id
     */
    @TableField("storehouseId")
    private Integer storehouseId;
    /**
     * 仓库名称(外表字段)
     */
    @TableField("storehouseName")
    private String storehouseName;
    /**
     * 药品id
     */
    @TableField("medicineId")
    private Integer medicineId;
    /**
     * 药品名称(外表字段)
     */
    @TableField("medicineName")
    private String medicineName;
    /**
     * 供应商id
     */
    @TableField("providerId")
    private Integer providerId;
    /**
     * 供应商名称(外表字段)
     */
    @TableField("providerName")
    private String providerName;
    /**
     * 库存数量
     */
    @TableField("quantity")
    private Integer quantity;
    /**
     * 单价
     */
    private BigDecimal money;
    /**
     * 库存价值
     */
    private BigDecimal totalPrice;
    /**
     * 删除标识
     */
    @TableField("isDel")
    @TableLogic
    private Integer isDel;



    /**
     * 报损数量(外表字段)
     */
    @TableField(exist = false)
    private Integer reportedNum;

    /**
     * 报损明细id(外表字段)
     */
    @TableField(exist = false)
    private Integer reporteddetailId;
}
