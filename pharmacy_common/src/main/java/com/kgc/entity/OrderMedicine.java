package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
    private String code;

    /**
     * 源订单编号
     */
    private String sourceCode;
    /**
     * 商品id
     */
    @TableField("medicineId")
    private Integer medicineid;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 商品总价
     */
    @TableField("totalPrice")
    private Double totalprice;

    @TableField("providerId")
    private Integer providerId;
}
