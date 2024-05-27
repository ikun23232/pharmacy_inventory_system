package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class CwAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水Id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 财务流水订单号
     */
      private String code;

    /**
     * 流水类别Id
     */
    @TableField("categoryId")
    private Integer categoryId;

    private String orderCode;

    /**
     * 流水总价
     */
    private BigDecimal cost;

    /**
     * 银行Id
     */
    @TableField("bankAcountId")
    private Integer bankAcountId;

    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private Date modificationTime;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;

    /**
     * 修改人
     */
    @TableField("modificationBy")
    private Integer modificationBy;

    /**
     * 逻辑删除
     */
    @TableField("isDel")
    private Integer isDel;




}
