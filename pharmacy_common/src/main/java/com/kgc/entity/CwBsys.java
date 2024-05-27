package com.kgc.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * 报损应收(CwBsys)实体类
 * 完整版(1.0)
 * @author lemon
 * @since 2024-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CwBsys implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 报损应收id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 报损应收编码
     */
    @TableField("code")
    private String code;
    /**
     * 原单号
     */
    @TableField("originalOrder")
    private String originalOrder;
    /**
     * 金额
     */
    @TableField("cost")
    private BigDecimal cost;
    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;
    /**
     * 创是否删除
     */
    @TableField("isDel")
    private int isDel;
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
    private Integer storehouseId;

    @TableField(exist = false)
    private String storehouseName;

    @TableField(exist = false)
    private Integer reportedTypeId;

    @TableField(exist = false)
    private String reportedTypeName;

    @TableField(exist = false)
    private String approverByName;

    @TableField(exist = false)
    private String createBy;

    @TableField(exist = false)
    private String createByName;

}
