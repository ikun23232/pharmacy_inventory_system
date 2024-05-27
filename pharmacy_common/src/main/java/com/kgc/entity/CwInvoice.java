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
public class CwInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发票id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发票单据
     */
    private String code;

    /**
     * 类型
     */
    @TableField("categoryId")
    private Integer categoryId;

    /**
     * 订单号
     */
    @TableField("orderNumber")
    private String orderNumber;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;

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
     * 修改人
     */
    @TableField("modificationBy")
    private Integer modificationBy;

    /**
     * 逻辑删除
     */
    private String isDel;

    /**
     * 发票总金额
     */
    private BigDecimal cost;


}
