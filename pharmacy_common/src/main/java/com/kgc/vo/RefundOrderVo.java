package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import com.kgc.entity.BaseMedicine;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class RefundOrderVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelFiled("单据编号")
    private String orderNo;

    @ExcelFiled("单据日期")
    private Date orderDate;

    @ExcelFiled("退款总金额")
    private BigDecimal totalPrice;

    @ExcelFiled("总数量")
    private Integer totalNumber;

    @ExcelFiled("退货原因")
    private String refundTypeName;
    @ExcelFiled("付款银行账户")
    private String bankAccountName;
    @ExcelFiled("备注")
    private String remark;
    @ExcelFiled("审批人")
    private String checkByName;
    @ExcelFiled("创建人")
    private String createByName;
    @ExcelFiled("修改人")
    private String updateByName;
    @ExcelFiled("修改时间")
    private Date updateDate;

    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    private List<BaseMedicine> baseMedicineList;


}
