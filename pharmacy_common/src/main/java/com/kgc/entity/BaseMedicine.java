package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

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
public class BaseMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医用商品名称
     */
    private String name;

    /**
     * 医用商品类型
     */
    @TableField("categoryId")
    private Integer categoryId;

    /**
     * 计量单位
     */
    @TableField("unitId")
    private Integer unitId;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
    private Integer isDel;

    /**
     * 零售价
     */
    private Double salePrice;

    /**
     * 医用商品规格
     */
    private String specification;

    /**
     * 库存预警值
     */
    private Integer warning;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;

    private Integer createBy;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date updateTime;

    private Integer updateBy;

    @TableField(exist=false)
    private String createByName;

    @TableField(exist=false)
    private String updateByName;

    @TableField(exist=false)
    private String categoryName;

    @TableField(exist=false)
    private String unitName;

    @TableField(exist=false)
    private Integer currentPage;

    @TableField(exist=false)
    private Integer quantity;

    @TableField(exist=false)
    private double totalPrice;

    @TableField(exist=false)
    private Integer providerId;

    @TableField(exist=false)
    private String providerName;

    @TableField(exist=false)
    private double purchasePrice;

    @TableField(exist=false)
    private String code;//单据编号

    /**
     * 原单号
     */
    private String  sourceCode;

    /**
     * 订单药品表id
     */
    private Integer medicineOrderId;

    private Integer medicineId;

    private int stock;

    @TableField(exist=false)
    private String batchCode;


    @TableField(exist=false)
    private String storehouseName;
}
