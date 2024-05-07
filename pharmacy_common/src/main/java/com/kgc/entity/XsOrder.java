package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

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
    private String orderno;

    /**
     * 销售订单日期
     */
    @TableField("orderDate")
    private LocalDate orderdate;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createby;

    /**
     * 0：销售 1：退款
     */
    private Integer type;

    /**
     * 销售订单总价格
     */
    @TableField("totalPrice")
    private Double totalprice;

    /**
     * 销售订单总数量
     */
    @TableField("totalNumber")
    private Integer totalnumber;

    /**
     * 银行账户id
     */
    @TableField("bankAccountId")
    private Integer bankaccountid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateby;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private LocalDate updatedate;

    /**
     * 0正常1作废
     */
    @TableField("isDelete")
    private Integer isdelete;

    /**
     * 0未审核1通过2不通过
     */
    @TableField("isCheck")
    private Integer ischeck;

    /**
     * 是否取消退款申请（0不取消1取消）
     */
    @TableField("isCancel")
    private Integer iscancel;

    /**
     * 退款类型id
     */
    @TableField("refundTypeId")
    private Integer refundtypeid;


}
