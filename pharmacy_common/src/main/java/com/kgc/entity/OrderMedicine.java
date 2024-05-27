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
public class OrderMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单-商品主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String sourceCode;
    private String code;

    /**
     * 商品id
     */
    @TableField("medicineId")
    private Integer medicineId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 商品总价
     */
    @TableField("totalPrice")
    private BigDecimal totalPrice;

    private Integer isDel;

    private String batchCode;

    @TableField("providerId")
    private Integer providerId;
    @TableField("fowardWarHouseId")
    private Integer fowardWarHouseId;
    @TableField(exist = false)
    private String fowardWarHouseName;

    @TableField(exist = false)
    private BigDecimal salePrice;
}
