package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 盘点单
 * </p>
 *
 * @author author
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kc_inventory")
public class KcInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单据编号
     */
    private String code;

    /**
     * 单据日期
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date billDate;

    /**
     * 仓库
     */
    private Integer warehouseId;

    /**
     * 物料分类
     */
    private Integer materialCategoryId;

    /**
     * 盘点人
     */
    private Integer checkerBy;
    @TableField(exist = false)
    private String checkerByName;



    /**
     * 附件
     */
    private String attachment;

    /**
     * 备注
     */
    private String remark;

    /**
     * 单据阶段(0代表编制未完成 1代表编制完成 2代表执行中 3代表执行完)
     */
    private Integer orderStatus;

    /**
     * 审核人
     */
    private Integer approverBy;
    @TableField(exist = false)
    private String approverByName;

    /**
     * 是否通过
     */
    private Integer isApproved;

    /**
     * 是否作废
     */
    private Integer isVoided;

    /**
     * 创建人
     */
    private Integer createBy;
    @TableField(exist = false)
    private String createByName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer updateBy;
    @TableField(exist = false)
    private String updateByName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 生效时间
     */
    private Date effectiveTime;

    /**
     * 删除标识
     */
    @TableLogic
    private Integer isdel;

    /**
     * 审核意见
     */
    private String approverRemark;

    /**
     * 主题
     */
    private String subject;

    @TableField(exist = false)
    private String warehouseName;


    @TableField(exist = false)
    private String materialCategoryName;

    @TableField(exist = false)
    private String orderStatusName;
    @TableField(exist = false)
    private Integer totalQuantity;

}
