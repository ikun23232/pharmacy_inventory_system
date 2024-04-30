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
public class CwAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水Id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流水类别Id
     */
    @TableField("accountsCategoryId")
    private Integer accountscategoryid;

    /**
     * 流水总价
     */
    private BigDecimal cost;

    /**
     * 订单Id
     */
    @TableField("orderId")
    private Integer orderid;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private LocalDateTime modificationtime;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 修改人
     */
    @TableField("modificationBy")
    private Integer modificationby;

    /**
     * 逻辑删除
     */
    @TableField("isDel")
    private String isdel;

    /**
     * 银行Id
     */
    @TableField("bankAcountId")
    private Integer bankacountid;

    /**
     * 描述
     */
    private String description;


}
