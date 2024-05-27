package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * 流水表(CwAccounts)实体类
 * 完整版(1.0)
 * @author lemon
 * @since 2024-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CwAccounts implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 流水Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 流水编号
     */
    @TableField("code")
    private String code;
    /**
     * 流水类别Id
     */
    @TableField("categoryId")
    private Integer categoryId;
    /**
     * 流水类别名称
     */
    @TableField(exist = false)
    private String accountsCategoryName;
    /**
     * 流水总价
     */
    private BigDecimal cost;
    /**
     * 订单Id
     */
    @TableField("orderCode")
    private String orderCode;
    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private LocalDateTime modificationTime;
    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;
    /**
     * 创建人姓名
     */
    @TableField(exist = false)
    private String createName;
    /**
     * 修改人
     */
    @TableField("modificationBy")
    private Integer modificationBy;
    /**
     * 修改人姓名
     */
    @TableField(exist = false)
    private String modificationName;
    /**
     * 逻辑删除
     */
    @TableField("isDel")
    private String isDel;
    /**
     * 银行卡Id
     */
    @TableField("bankAcountId")
    private Integer bankAcountId;
    /**
     * 银行卡号
     */
    @TableField(exist = false)
    private String bandCount;
    /**
     * 描述
     */
    @TableField("description")
    private String description;
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


}
