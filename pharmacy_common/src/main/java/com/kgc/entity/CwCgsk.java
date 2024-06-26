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
public class CwCgsk implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单据编号
     */
    private String code;

    /**
     * 单据日期
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 原单号
     */
    @TableField("originalOrder")
    private String originalorder;

    /**
     * 应付金额
     */
    private BigDecimal cost;

    /**
     * 供应商
     */
    private String provider;

    /**
     * 仓库员
     */
    @TableField("createBy")
    private String createby;

    /**
     * 逻辑删除
     */
    private String isdel;

    /**
     * 药品
     */
    private String medicine;


}
