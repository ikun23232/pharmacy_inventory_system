package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class KcDispatch implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度申请id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 调度表编号
     */
    private String code;

    /**
     * 调度主题
     */
    private String subject;

    /**
     * 调度的总价格
     */
    private Double price;

    /**
     * 原仓库Id
     */
    @TableField("beforeWarehouseId")
    private Integer beforeWarehouseId;

    @TableField(exist = false)
    private String beforeWareName;
    /**
     * 目标仓库Id
     */
    @TableField("AimWarehouseId")
    private Integer AimWarehouseId;

    @TableField(exist = false)
    private String AimWareName;
    /**
     * 备注
     */
    private String remark;

    /**
     * 审批人
     */
    @TableField("approverBy")
    private Integer approverBy;

    @TableField(exist = false)
    private String approverName;

    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @TableField("approvalStatus")
    private Integer approvalStatus;

    /**
     * 核批备注
     */
    @TableField("approverRemark")
    private String approverRemark;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    private Integer documenterBy;

    /**
     * 作废状态
     */
    @TableField("voidState")
    private Integer voidState;

    /**
     * 创造时间
     */
    @TableField("createDate")
    private Date createDate;


    @TableField(exist = false)
    private String createName;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private Date updateDate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    @TableField(exist = false)
    private String updateName;

    private Integer orderStatus;

    private String orderStatusResult;

    private Integer isDel;//删除id
}
