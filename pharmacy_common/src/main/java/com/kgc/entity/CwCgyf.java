package com.kgc.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @TableField("cgdd_code")
    private String cgddCode;
    /**
     * 供应商id
     */
    @TableField("provider_id")
    private Integer providerId;
    /**
     * 采购应付状态(付款状态(0未知,1待付款,已付款))
     */
    @TableField("is_pay")
    private Integer isPay;
    /**
     * 采购应付金额
     */
    @TableField("cost")
    private BigDecimal cost;
    /**
     * 采购应付生成时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 付款时间
     */
    @TableField("payment_time")
    private LocalDateTime paymentTime;
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

}