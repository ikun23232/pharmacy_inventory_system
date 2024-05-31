package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private BigDecimal price;

    /**
     * 原仓库Id
     */
    @TableField("beforeWarehouseId")
    private Integer beforeWarehouseId;

    @TableField(exist = false)
    private String beforeWareName;

    @TableField("dispatchBy")
    private Integer dispatchBy;

    @TableField(exist = false)
    private String dispatchName;
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
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    @TableField(exist = false)
    private String createName;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    @TableField(exist = false)
    private String updateName;

    @TableField("orderStatus")
    private Integer orderStatus;

    @TableField(exist = false)
    private String orderStatusResult;

    @TableLogic
    private Integer isDel;//删除id

    @TableField(exist = false)
    private List<BaseMedicine> medicineList;

    @TableField(exist = false)
    private Integer isCommit;

    @TableField("dispatchTime")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dispatchTime;//调度时间

    @TableField("totalCount")
    private Integer totalCount;
}
