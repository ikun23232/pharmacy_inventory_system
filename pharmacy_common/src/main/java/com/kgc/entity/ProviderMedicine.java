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
public class ProviderMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商药品表主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商id
     */
    @TableField("providerId")
    private String providerId;

    /**
     * 药品id
     */
    @TableField("medicineId")
    private Integer medicineId;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;
}
