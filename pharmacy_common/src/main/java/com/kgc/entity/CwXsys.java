package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * 销售应收表(CwXsys)实体类
 * 完整版(1.1)
 * @author lemon
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CwXsys implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 销售应收id
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
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 原单号
     */
    @TableField("originalOrder")
    private String originalOrder;
    /**
     * 药品名
     */
    private String medicine;
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
    /**
     * 银行账户(外表字段)
     */
    private String bandCount;
    /**
     * 审核意见(外表字段)
     */
    private String opinion;


}
