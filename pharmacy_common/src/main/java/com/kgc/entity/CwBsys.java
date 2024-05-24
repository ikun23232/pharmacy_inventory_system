package com.kgc.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 报损应收
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CwBsys {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("createBy")
    private String code;
    @TableField("originalOrder")
    private String originalOrder;
    @TableField("cost")
    private BigDecimal cost;
    @TableField("createTime")
    private LocalDateTime createTime;
    @TableField("isDel")
    private int isDel;

}
