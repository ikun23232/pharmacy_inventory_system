package com.kgc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
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
    @ExcelProperty("采购编号")
    private String code;

    /**
     * 主题
     */
    @ExcelProperty("采购编号")
    private String subject;

    /**
     * 采购类型
     */
    private Integer type;

    /**
     * 采购类型
     */
    private String cgType;

    @TableField(exist = false)
    @ExcelProperty("采购编号")
    private String OrderTypeName;//订单类型名字

    /**
     * 需求人
     */
    @TableField("demanderBy")
    @ExcelIgnore
    private Integer demanderBy;

    @ExcelProperty("采购编号")
    private String demanderName;//需求人名字

    /**
     * 需求日期
     */
    @TableField("demandTime")
    @ExcelProperty("需求日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date demandTime;

    /**
     * 数量
     */
    @ExcelProperty("数量")
    @NumberFormat("#")
    private Integer count;

    /**
     * 参考金额
     */
    @TableField("referenceAmount")
    @ExcelProperty("参考金额")
    @NumberFormat("#.##")
    private Double referenceAmount;

    /**
     * 单据阶段(0代表编制未完成 1代表编制完成)
     */
    @TableField("orderStatus")
    @ExcelIgnore
    private Integer orderStatus;

    @ExcelProperty("参考金额")
    private String OrderStatueType;

    /**
     * 审批状态(0代表审批未通过,1代表审批通过)
     */
    @TableField("approvalStatus")
    @ExcelIgnore
    private Integer approvalStatus;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;

    /**
     * 生效时间
     */
    @TableField("effectiveTime")
    @ExcelProperty("需求日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date effectiveTime;

    /**
     * 核批人
     */
    @TableField("approverBy")
    @ExcelIgnore
    private Integer approverBy;

    @ExcelProperty("核批人")
    private String approverName;//核批人名字

    /**
     * 核批意见
     */
    @TableField("approverRemark")
    @ExcelProperty("核批意见")
    private String approverRemark;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    @ExcelIgnore
    private Integer documenterBy;

    @TableField(exist = false)
    @ExcelProperty("制单人")
    private String documenterName;
    /**
     * 作废状态
     */
    @TableField("voidState")
    @ExcelIgnore
    private int voidState;

    /**
     * 制单时间
     */
    @TableField("createTime")
    @DateTimeFormat("yyyy-MM-dd")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    @ExcelIgnore
    private Integer updateBy;

    @TableField(exist = false)
    @ExcelProperty("修改人")
    private String updateName;//修改人名字

    /**
     * 修改时间
     */
    @TableField("updateTime")
    @DateTimeFormat("yyyy-MM-dd")
    private Date updateTime;

    /**
     * 供应商编号
     */
    @TableField("providerId")
    @ExcelIgnore
    private Integer providerId;

    @TableField(exist = false)
    @ExcelProperty("供应商名称")
    private String providerName;//供应商名字
    /**
     * 联系人
     */
    @ExcelProperty("联系人")
    private String contactperson;

    /**
     * 联系电话
     */
    @ExcelProperty("联系电话")
    private String phone;

    /**
     * 传真
     */
    @ExcelProperty("传真")
    private String fax;

    /**
     * 邮件
     */
    @ExcelProperty("邮件")
    private String email;

    /**
     * 交货日期
     */
    @TableField("deliveryDate")
    @ExcelProperty("交货日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * 交货人
     */
    @TableField("deliveryBy")
    private Integer deliveryBy;

    @TableField(exist = false)
    @ExcelProperty("交货人")
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
    @DateTimeFormat("yyyy-MM-dd")
    private Date payTime;

    /**
     * 支付状态(0-未支付 1-已支付)
     */
    @TableField("isPay")
    private int isPay;

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

    @TableField(exist = false)
    private List<BaseMedicine> medicineList;
}
