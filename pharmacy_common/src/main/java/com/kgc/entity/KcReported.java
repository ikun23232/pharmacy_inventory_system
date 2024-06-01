package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * 库存报损(KcReported)实体类
 * 完整版(1.0)
 * @author lemon
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KcReported implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 报损订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 报损编号
     */
    @TableField("code")
    private String code;

    @TableField("title")
    private String title;
    /**
     * 仓库Id
     */
    @TableField("storehouseId")
    private Integer storehouseId;
    /**
     * 仓库名称(外表字段)
     */
    @TableField(exist = false)
    private String storehouseName;
    /**
     * 报损类型id
     */
    @TableField("reportedTypeId")
    private Integer reportedTypeId;
    /**
     * 报损类型名称(外表字段)
     */
    @TableField(exist = false)
    private String reportedTypeName;
    /**
     * 审批人
     */
    @TableField("approverBy")
    private int approverBy;
    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @TableField("approvalStatus")
    private Integer approvalStatus;
    /**
     * 审批标注
     */
    @TableField("approverRemark")
    private String approverRemark;
    /**
     * 制单人
     */
    @TableField("documenterBy")
    private int documenterBy;
    /**
     * 报损时间
     */
    @TableField("createTime")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("createBy")
    private int createBy;
    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private Date modificationTime;
    /**
     * 修改人
     */
    @TableField("modificationBy")
    private int modificationBy;
    /**
     * 删除
     */
    @TableField("isDel")
    private int isDel;
    /**
     * 是否作废
     */
    @TableField("isCan")
    private Integer isCan;
    /**
     * 审批人姓名(外表字段)
     */
    @TableField(exist = false)
    private String approverName;
    /**
     * 制单人姓名(外表字段)
     */
    @TableField(exist = false)
    private String documenterName;
    /**
     * 创建人姓名(外表字段)
     */
    @TableField(exist = false)
    private String createName;
    /**
     * 修改人姓名(外表字段)
     */
    @TableField(exist = false)
    private String modificationName;
    /**
     * 开始时间(模糊查询createTime)
     */
    @TableField(exist = false)
    private String beginTime;
    /**
     * 结束时间(模糊查询createTime)
     */
    @TableField(exist = false)
    private String endTime;

    @TableField(exist = false)
    private List<KcMedicine> medicineList;

}
