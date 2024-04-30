package com.kgc.entity;

import java.math.BigDecimal;
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
public class CwCgyf implements Serializable {

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
     * 采购员
     */
    @TableField("createBy")
    private String createby;

    /**
     * 逻辑删除
     */
    private String isdel;


}
