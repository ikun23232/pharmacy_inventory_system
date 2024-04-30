package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class KcReported implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报损id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报损编号
     */
    private String code;

    /**
     * 仓库Id
     */
    @TableField("warehouseId")
    private Integer warehouseid;

    /**
     * 报损类型id
     */
    @TableField("reportedTypeId")
    private Integer reportedtypeid;

    /**
     * 报损明细Id
     */
    @TableField("reportedDetailId")
    private String reporteddetailid;

    /**
     * 审批人
     */
    @TableField("approverBy")
    private String approverby;

    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @TableField("approvalStatus")
    private Integer approvalstatus;

    /**
     * 审批标注
     */
    @TableField("approverRemark")
    private String approverremark;

    /**
     * 制单人
     */
    @TableField("documenterBy")
    private String documenterby;

    /**
     * 报损时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 创建人
     */
    @TableField("createBy")
    private String createby;

    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private LocalDateTime modificationtime;

    /**
     * 修改人
     */
    @TableField("modificationBy")
    private String modificationby;

    /**
     * 删除
     */
    @TableField("isDel")
    private String isdel;


}
