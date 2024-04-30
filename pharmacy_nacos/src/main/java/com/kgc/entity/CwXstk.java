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
    private LocalDateTime createtime;

    /**
     * 原单号
     */
    @TableField("originalOrder")
    private String originalorder;

    /**
     * 退货原因
     */
    @TableField("backReson")
    private String backreson;

    /**
     * 金额
     */
    private BigDecimal cost;

    /**
     * 是否删除
     */
    private String isdel;

    /**
     * 销售员
     */
    @TableField("createBy")
    private String createby;


}
