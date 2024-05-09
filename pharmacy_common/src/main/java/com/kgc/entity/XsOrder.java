package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
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
public class XsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售订单id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 销售订单编号
     */
    @TableField("orderNo")
    private String orderNo;

    /**
     * 销售订单日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField("orderDate")
    private LocalDate orderDate;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;

    /**
     * 0：销售 1：退款
     */
    private Integer type;

    /**
     * 销售订单总价格
     */
    @TableField("totalPrice")
    private Double totalPrice;

    /**
     * 销售订单总数量
     */
    @TableField("totalNumber")
    private Integer totalNumber;

    /**
     * 银行账户id
     */
    @TableField("bankAccountId")
    private Integer bankAccountId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private LocalDate updateDate;

    /**
     * 是否删除
     */
    // @TableLogic 用来标注表示逻辑删除的字段 value是表示未删除的值 delval是表示删除的值
    @TableLogic(value = "0",delval = "1")
    @TableField("isDelete")
    private Integer isDelete;

    /**
     * 0未审核1通过2不通过
     */
    @TableField("isCheck")
    private Integer isCheck;

    /**
     * 是否取消退款申请（0不取消1取消）
     */
    @TableField("isCancel")
    private Integer isCancel;

    /**
     * 退款类型id
     */
    @TableField("refundTypeId")
    private Integer refundTypeId;
    /**
     * 编辑状态
     */
    private Integer editStatus;
    /**
     * 取消状态
     */
    private Integer cancelStatus;


    @TableField(exist=false)
    private String createByName;
    @TableField(exist=false)
    private String updateByName;
    @TableField(exist=false)
    private String bankAccountName;
    @TableField(exist=false)
    private Integer currentPage;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderDateBegin;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderDateEnd;




}
