package com.kgc.entity;

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

    /**
     * 需求人
     */
    @TableField("demanderBy")
    private Integer demanderby;

    /**
     * 需求日期
     */
    @TableField("demandTime")
    private LocalDateTime demandtime;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 参考金额
     */
    @TableField("referenceAmount")
    private Double referenceamount;

    /**
     * 单据阶段(0代表编制未完成 1代表编制完成)
     */
    @TableField("orderStatus")
    private Integer orderstatus;

    /**
     * 审批状态(0代表审批未通过,1代表审批通过)
     */
    @TableField("approvalStatus")
    private Integer approvalstatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 生效时间
     */
    @TableField("effectiveTime")
    private LocalDateTime effectivetime;

    /**
     * 核批人
     */
    @TableField("approverBy")
    private Integer approverby;

    /**
     * 核批意见
     */
    @TableField("approverRemark")
    private String approverremark;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    private Integer documenterby;

    /**
     * 作废状态
     */
    @TableField("voidState")
    private String voidstate;

    /**
     * 制单时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private LocalDateTime updatetime;

    /**
     * 供应商编号
     */
    @TableField("providerId")
    private Integer providerid;

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
    private LocalDateTime deliverydate;

    /**
     * 交货人
     */
    @TableField("deliveryBy")
    private Integer deliveryby;

    /**
     * 结算方式(0-货到付款 1-全款后发货)
     */
    @TableField("payType")
    private Integer paytype;

    /**
     * 结算日期
     */
    @TableField("payTime")
    private LocalDateTime paytime;

    /**
     * 支付状态(0-未支付 1-已支付)
     */
    @TableField("isPay")
    private String ispay;


}
