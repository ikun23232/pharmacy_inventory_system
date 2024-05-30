package com.kgc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @ExcelFiled("采购入库编号")
    private String code;

    /**
     * 药品入库总价格
     */
    @TableField("medicinePrice")
    @ExcelFiled("库存总价值")
    private Double medicineprice;

    /**
     * 采购单Id
     */
    @TableField("purchaseId")
    private Integer purchaseid;

    @TableField(exist = false)
    @ExcelFiled("供应商")
    private String providerName;


    /**
     * 备注
     */
    @ExcelFiled("备注")
    private String remark;

    /**
     * 生效时间
     */
    @TableField("effectiveTime")
    @ExcelFiled("生效时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd mm:hh:ss")
    private Date effectiveTime;

    /**
     * 审批人
     */
    @TableField("approverBy")
    private Integer approverby;
    @TableField(exist = false)
    @ExcelFiled("核批人")
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
    @ExcelFiled("核批结果")
    private String approverremark;

    /**
     * 是否入库 1：入库 0：未入库
     */
    @TableField("isAddWarehouse")
    @ExcelFiled("是否入库")
    private Integer isaddwarehouse;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    private Integer documenterby;
    @TableField(exist = false)
    @ExcelFiled("制单人")
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
    @ExcelFiled("创造时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd mm:hh:ss")
    private Date createTime;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createby;
    @TableField(exist = false)
    @ExcelFiled("需求人")
    private String demanderUserName;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    @ExcelFiled("修改时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd mm:hh:ss")

    private Date updateTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;
    @TableField(exist = false)
    @ExcelFiled("修改人")
    private String updateUserName;



    /**
     * 删除id 0：未删除 1：删除
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;
    @TableField("subject")
    @ExcelFiled("单据主题")
    private String subject;

    @TableField(exist = false)
    @ExcelFiled("单据状态")
    private String orderStatueName;

    @TableField(exist = false)
    @ExcelFiled("源单号")
    private String sourceCode;
    @TableField(exist = false)
    @ExcelFiled("入库总数")
    private Integer count;
    @TableField(exist = false)
    @ExcelFiled("参考金额")
    private double referenceAmount;
    @TableField(exist = false)
    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    private List<BaseMedicine> medicineList;
    private  Integer orderStatus;
    private  Integer providerId;
    private Integer warhouseUserId;
    @TableField(exist = false)
    @ExcelFiled("库管员")
    private String warhouseUserName;
    @ExcelFiled("入库金额")
    @TableField(exist = false)
    private BigDecimal formMoney;
    @ExcelFiled("结算金额")
    @TableField(exist = false)
    private BigDecimal payMoney;




}
