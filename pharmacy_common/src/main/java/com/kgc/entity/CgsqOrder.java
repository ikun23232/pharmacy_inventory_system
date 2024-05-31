package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import com.kgc.vo.MedicineVO;
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
    @ExcelFiled("采购编号")
    private String code;

    /**
     * 主题
     */
    @ExcelFiled("采购主题")

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
    @ExcelFiled("需求日期")


    private Date demandtime;

    /**
     * 数量
     */
    @ExcelFiled("数量")

    private Integer count;

    /**
     * 参考金额
     */
    @TableField("referenceAmount")
    @ExcelFiled("参考金额")

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
    @ExcelFiled("备注")

    private String remark;

    /**
     * 生效时间
     */
    @TableField("effectiveTime")
    @ExcelFiled("生效时期")

    private Date effectivetime;

    /**
     * 核批人
     */
    @TableField("approverBy")
    private Integer approverby;

    /**
     * 核批意见
     */
    @TableField("approverRemark")
    @ExcelFiled("核批意见")

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
     * 制单时间
     */
    @TableField("createTime")
    @ExcelFiled("制单时间")
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
    @ExcelFiled("修改时间")

    private Date updatetime;
    /**
     * 删除标识
     */
    @TableLogic
    @TableField("isDel")
    private Integer isdel;


    @TableField(exist = false)
    @ExcelFiled("制单人")

    private String demanderUserName;
    @TableField(exist = false)
    @ExcelFiled("核批人")

    private String approverUserName;

    @TableField(exist = false)

    private String documenterUserName;
    @TableField(exist = false)
    @ExcelFiled("修改人")

    private String updateUserName;
    @TableField(exist = false)
    @ExcelFiled("采购类型")

    private String cgtype;

    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    @TableField(exist = false)
    private List<BaseMedicine> MedicineList;
    @ExcelFiled("单据状态")

    @TableField(exist = false)
    private String orderStatueName;






}
