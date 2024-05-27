package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import com.kgc.vo.MedicineVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    @ExcelFiled("单据编号")
    private String orderNo;

    /**
     * 销售订单日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField("orderDate")
    @ExcelFiled("单据日期")
    private Date orderDate;

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
    @ExcelFiled("总金额")
    private BigDecimal totalPrice;

    /**
     * 销售订单总数量
     */
    @TableField("totalNumber")
    @ExcelFiled("总数量")
    private Integer totalNumber;

    /**
     * 银行账户id
     */
    @TableField("bankAccountId")
    private Integer bankAccountId;

    /**
     * 备注
     */
    @ExcelFiled("备注")
    private String remark;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField("updateDate")
    @ExcelFiled("修改时间")
    private Date updateDate;

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

    private String opinion;

    private Integer checkBy;
//    /**
//     * 是否损坏
//     */
//    private Integer isBad;

    private String checkByName;

    @TableField(exist=false)
    @ExcelFiled("创建人")
    private String createByName;
    @TableField(exist=false)
    @ExcelFiled("修改人")
    private String updateByName;
    @TableField(exist=false)
    @ExcelFiled("银行账户")
    private String bankAccountName;
    @TableField(exist=false)
    private String name;
    @TableField(exist=false)
    private String belongBank;

    @TableField(exist=false)
    private Integer currentPage;

    @TableField(exist=false)
    private String orderDateBegin;
    @TableField(exist=false)
    private String orderDateEnd;

    @TableField(exist=false)
    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    private List<BaseMedicine> baseMedicineList;

    @TableField(exist=false)
    private List<OrderMedicine> checkedDetail;
    @TableField(exist=false)
    private List<OrderMedicine> medicineDetailList;

    @TableField(exist=false)
    private String refundTypeName;


}
