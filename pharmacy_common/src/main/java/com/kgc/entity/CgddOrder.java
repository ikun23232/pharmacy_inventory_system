package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
public class CgddOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购订单主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 采购编号
     */
    private String code;

    /**
     * 主题
     */
    private String subject;

    /**
     * 采购类型
     */
    private Integer type;

    @TableField(exist = false)
    private String OrderTypeName;//订单类型名字

    /**
     * 需求人
     */
    @TableField("demanderBy")
    private Integer demanderBy;

    private String demanderName;//需求人名字

    /**
     * 需求日期
     */
    @TableField("demandTime")
    private Date demandTime;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 参考金额
     */
    @TableField("referenceAmount")
    private Double referenceAmount;

    /**
     * 单据阶段(0代表编制未完成 1代表编制完成)
     */
    @TableField("orderStatus")
    private Integer orderStatus;

    /**
     * 审批状态(0代表审批未通过,1代表审批通过)
     */
    @TableField("approvalStatus")
    private Integer approvalStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 生效时间
     */
    @TableField("effectiveTime")
    private Date effectiveTime;

    /**
     * 核批人
     */
    @TableField("approverBy")
    private Integer approverBy;

    private String approverName;//核批人名字

    /**
     * 核批意见
     */
    @TableField("approverRemark")
    private String approverRemark;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    private Integer documenterBy;

    @TableField(exist = false)
    private String documenterName;
    /**
     * 作废状态
     */
    @TableField("voidState")
    private String voidState;

    /**
     * 制单时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    @TableField(exist = false)
    private String updateName;//修改人名字

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 供应商编号
     */
    @TableField("providerId")
    private Integer providerId;

    @TableField(exist = false)
    private String providerName;//供应商名字
    /**
     * 联系人
     */
    private String contactperson;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 邮件
     */
    private String email;

    /**
     * 交货日期
     */
    @TableField("deliveryDate")
    private Date deliveryDate;

    /**
     * 交货人
     */
    @TableField("deliveryBy")
    private Integer deliveryBy;

    @TableField(exist = false)
    private String deliveryName;//交货人名字

    /**
     * 结算方式(0-货到付款 1-全款后发货)
     */
    @TableField("payType")
    private Integer payType;

    /**
     * 结算日期
     */
    @TableField("payTime")
    private Date payTime;

    /**
     * 支付状态(0-未支付 1-已支付)
     */
    @TableField("isPay")
    private String isPay;

    @TableField(exist = false)
    private String beginTime;

    @TableField(exist = false)
    private Date beginDate;

    @TableField(exist = false)
    private String endTime;

    @TableField(exist = false)
    private Date endDate;

    @TableLogic
    @TableField("isDel")
    private int isDel;
}
