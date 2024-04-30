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

    /**
     * 备注
     */
    private String remark;

    /**
     * 生效时间
     */
    private LocalDateTime datetime;

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

    /**
     * 删除id 0：未删除 1：删除
     */
    @TableField("isDel")
    private Integer isdel;


}
