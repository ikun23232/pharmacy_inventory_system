package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * 发票表(CwInvoice)实体类
 * 完整版(1.1)
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
//    /**
//     * 发票编号
//     */
//    @TableField("InvoiceNumber")
//    private Integer InvoiceNumber;
    /**
     * 类型
     */
    @TableField("categoryId")
    private Integer categoryId;
    /**
     * 类型名称
     */
    @TableField(exist = false)
    private String categoryName;
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
     * 创建人名称
     */
    @TableField("createName")
    private String createName;
    /**
     * 创建时间
     */
    @TableField("createTime")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modificationTime")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modificationTime;
    /**
     * 修改人
     */
    @TableField("modificationBy")
    private Integer modificationBy;
    /**
     * 修改人名称
     */
    @TableField("modificationName")
    private String modificationName;
    /**
     * 发票总金额
     */
    private BigDecimal cost;
    /**
     * 开始时间(模糊查询createTime)
     */
    @TableField(exist = false)
    private String beginTime;
    /**
     * 结束时间(模糊查询createTime)
     */
    @TableField(exist = false)
    private String endTime;

}
