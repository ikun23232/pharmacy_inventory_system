package com.kgc.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
     * 发票编号
     */
    @TableField("InvoiceNumber")
    private Integer invoicenumber;

    /**
     * 类型
     */
    @TableField("categoryId")
    private String categoryid;

    /**
     * 订单号
     */
    @TableField("orderNumber")
    private Integer ordernumber;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private LocalDateTime modificationtime;

    /**
     * 修改人
     */
    @TableField("modificationBy")
    private Integer modificationby;

    /**
     * 逻辑删除
     */
    private String isdel;

    /**
     * 发票总金额
     */
    private BigDecimal cost;


}
