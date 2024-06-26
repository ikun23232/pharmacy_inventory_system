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
 *
 * 采购应付表(CwCgyf)实体类
 * 完整版(1.0)
 * @author lemon
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CwCgyf implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 采购应付id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 采购应付编号
     */
    @TableField("code")
    private String code;
    /**
     * 采购订单编号
     */
    @TableField("cgddCode")
    private String cgddCode;
    /**
     * 供应商id
     */
    @TableField("providerId")
    private Integer providerId;
    /**
     * 采购应付状态(付款状态(0未知,1待付款,已付款))
     */
    @TableField("isPay")
    private Integer isPay;
    /**
     * 采购应付金额
     */
    @TableField("cost")
    private BigDecimal cost;
    /**
     * 采购应付生成时间
     */
    @TableField("createTime")
    private Date createTime;
    /**
     * 付款时间
     */
    @TableField("paymentTime")
    private Date paymentTime;
    /**
     * 供应商名称
     */
    @TableField(exist = false)
    private String providerName;
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

    /**
     * 采购订单id
     */
    @TableField(exist = false)
    private Integer cgddId;
    /**
     * 采购订单创建人
     */
    @TableField(exist = false)
    private String demanderName;
    /**
     * 采购订单创建时间
     */
    @TableField(exist = false)
    private Date demandTime;
    /**
     * 采购订单主题
     */
    @TableField(exist = false)
    private String subject;
    /**
     * 付款人id
     */
    @TableField(exist = false)
    private Integer payUserId;
}