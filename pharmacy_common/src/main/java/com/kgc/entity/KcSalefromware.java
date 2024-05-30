package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
public class KcSalefromware implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售出库id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 销售出库单据编号
     */
    private String code;

    /**
     * 销售订单编号
     */
    @TableField("orderNo")
    private String orderNo;

    @TableLogic
    private Integer isDel;

    @TableField(exist=false)
    private XsOrder xsOrder;

    @TableField(exist=false)
    private int currentPage;
    @TableField(exist=false)
    private String orderDateBegin;
    @TableField(exist=false)
    private String orderDateEnd;
    @TableField(exist=false)
    private String createByName;
    @TableField(exist=false)
    private Integer createBy;




}
