package com.kgc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelIgnore
    private Integer id;

    /**
     * 单据编号
     */
    @ExcelProperty("采购入库编号")
    private String code;

    /**
     * 药品入库总价格
     */
    @TableField("medicinePrice")
    @ExcelProperty("库存总价值")
    private Double medicineprice;

    /**
     * 采购单Id
     */
    @TableField("purchaseId")
    @ExcelIgnore
    private Integer purchaseid;

    @TableField(exist = false)
    @ExcelProperty("供应商")
    private String providerName;


    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;

    /**
     * 生效时间
     */
    @TableField("effectiveTime")
    @ExcelProperty("生效时间")
    private Date effectiveTime;

    /**
     * 审批人
     */
    @TableField("approverBy")
    @ExcelIgnore
    private Integer approverby;
    @TableField(exist = false)
    @ExcelProperty("核批人")
    private String approverUserName;

    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @TableField("approvalStatus")
    @ExcelIgnore
    private Integer approvalstatus;

    /**
     * 核批结果
     */
    @TableField("approverRemark")
    @ExcelProperty("核批结果")
    private String approverremark;

    /**
     * 是否入库 1：入库 0：未入库
     */
    @TableField("isAddWarehouse")
    @ExcelProperty("是否入库")
    private Integer isaddwarehouse;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    @ExcelIgnore
    private Integer documenterby;
    @TableField(exist = false)
    @ExcelProperty("制单人")
    private String documenterUserName;

    /**
     * 作废状态
     */
    @TableField("voidState")
    @ExcelIgnore
    private Integer voidstate;

    /**
     * 创造时间
     */
    @TableField("createTime")
    @ExcelProperty("创造时间")
    private Date createTime;

    /**
     * 创造人
     */
    @TableField("createBy")
    @ExcelIgnore
    private Integer createby;
    @TableField(exist = false)
    @ExcelProperty("需求人")
    private String demanderUserName;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    @ExcelProperty("修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    @ExcelIgnore
    private Integer updateby;
    @TableField(exist = false)
    @ExcelProperty("修改人")
    private String updateUserName;



    /**
     * 删除id 0：未删除 1：删除
     */
    @TableLogic
    @TableField("isDel")
    @ExcelIgnore
    private Integer isdel;
    @TableField("subject")
    @ExcelProperty("单据主题")
    private String subject;

    @TableField(exist = false)
    @ExcelProperty("单据状态")
    private String orderStatueName;

    @TableField(exist = false)
    @ExcelProperty("源单号")
    private String sourceCode;
    @TableField(exist = false)
    @ExcelProperty("入库总数")
    private int count;
    @TableField(exist = false)
    @ExcelProperty("参考金额")
    private double referenceAmount;
    @TableField(exist = false)
    @ExcelIgnore
    private List<BaseMedicine> medicineList;
    @ExcelIgnore
    private  int orderStatus;
    @ExcelIgnore
    private  int providerId;
    @ExcelIgnore
    private int warhouseUserId;
    @TableField(exist = false)
    @ExcelProperty("库管员")
    private String warhouseUserName;









}
