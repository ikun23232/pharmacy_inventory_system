package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Integer beforewarehouseid;

    /**
     * 目标仓库Id
     */
    @TableField("AimWarehouseId")
    private Integer aimwarehouseid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审批人
     */
    @TableField("approverBy")
    private Integer approverby;

    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @TableField("approvalStatus")
    private Integer approvalstatus;

    /**
     * 核批备注
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
    private Integer voidstate;

    /**
     * 创造时间
     */
    @TableField("createDate")
    private LocalDateTime createdate;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private LocalDateTime updatedate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;


}
