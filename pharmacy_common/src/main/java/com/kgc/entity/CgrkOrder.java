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
public class CgrkOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单据编号
     */
    private String code;

    /**
     * 药品入库总价格
     */
    @TableField("medicinePrice")
    private Double medicineprice;

    /**
     * 采购单Id
     */
    @TableField("purchaseId")
    private Integer purchaseid;

    @TableField(exist = false)
    private String providerName;


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
     * 审批人
     */
    @TableField("approverBy")
    private Integer approverby;
    @TableField(exist = false)
    private String approverUserName;

    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @TableField("approvalStatus")
    private Integer approvalstatus;

    /**
     * 核批结果
     */
    @TableField("approverRemark")
    private String approverremark;

    /**
     * 是否入库 1：入库 0：未入库
     */
    @TableField("isAddWarehouse")
    private Integer isaddwarehouse;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    private Integer documenterby;
    @TableField(exist = false)
    private String documenterUserName;

    /**
     * 作废状态
     */
    @TableField("voidState")
    private Integer voidstate;

    /**
     * 创造时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;
    @TableField(exist = false)
    private String demanderUserName;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;
    @TableField(exist = false)
    private String updateUserName;



    /**
     * 删除id 0：未删除 1：删除
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;
    @TableField("subject")
    private String subject;

    @TableField(exist = false)
    private String orderStatueName;

    @TableField(exist = false)
    private String sourceCode;
    @TableField(exist = false)
    private int count;
    @TableField(exist = false)
    private double referenceAmount;
    @TableField(exist = false)
    private List<BaseMedicine> medicineList;
    private  int orderStatus;
    private  int providerId;
    private int warhouseUserId;
    @TableField(exist = false)
    private String warhouseUserName;









}
