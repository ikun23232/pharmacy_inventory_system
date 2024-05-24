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
 * <p>
 * 
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CwXstk implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id	id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单据编号
     */
    private String code;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 原单号
     */
    @TableField("originalOrder")
    private String originalOrder;

    /**
     * 退货原因
     */
    @TableField("backReson")
    private String backReson;

    /**
     * 金额
     */
    private BigDecimal cost;

    /**
     * 是否删除
     */
    private String isDel;

    /**
     * 销售员
     */
    @TableField("createBy")
    private String createBy;

    /**
     * 销售员名称
     */
    @TableField(exist = false)
    private String createName;

}
