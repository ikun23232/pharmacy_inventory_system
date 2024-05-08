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
public class CgsqOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购申请订单主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 采购申请编号
     */
    private String code;

    /**
     * 主题
     */
    private String subject;

    /**
     * 源单号
     */
    @TableField("sourceId")
    private Integer sourceid;

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
     * 单据阶段(0代表编制未完成 1代表编制完成 2代表执行中 3代表执行完)
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
    private Date createtime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private Date updatetime;
    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;


    @TableField(exist = false)
    private String demanderUserName;
    @TableField(exist = false)
    private String approverUserName;

    @TableField(exist = false)
    private String documenterUserName;
    @TableField(exist = false)
    private String updateUserName;




}
