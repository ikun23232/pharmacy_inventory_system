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
 * <p>
 * 
 * </p>
 *
 * @author lemon
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 银行账户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 银行账户
     */
    @TableField("bandCount")
    private String bandCount;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 账户名
     */
    private String name;

    /**
     * 创建时间
     */
    @TableField("createTime")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;

    @TableField(exist=false)
    private String createMan;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;
    @TableField(exist=false)
    private String updateMan;

    /**
     * 备注
     */
    private String tip;

    /**
     * 是否删除
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 所属银行
     */
    @TableField("belongBank")
    private String belongBank;


}
