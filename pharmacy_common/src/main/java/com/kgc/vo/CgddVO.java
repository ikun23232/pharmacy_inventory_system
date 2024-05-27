package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/24 8:32
 */
@Data
public class CgddVO implements Serializable {
    @ExcelFiled("采购编号")
    private String code;
    @ExcelFiled("采购主题")
    private String subject;
    @ExcelFiled("订单类型")
    private String orderTypeName;//订单类型名字
    @ExcelFiled("需求人")
    private String demanderName;//需求人名字
    @ExcelFiled("需求日期")
    private Date demandTime;
    @ExcelFiled("数量")
    private Integer count;
    @ExcelFiled("参考金额")
    private BigDecimal referenceAmount;
    @ExcelFiled("单据阶段")
    private String OrderStatueType;
    @ExcelFiled("备注")
    private String remark;
    @ExcelFiled("需求日期")
    private Date effectiveTime;
    @ExcelFiled("核批人")
    private String approverName;//核批人名字
    @ExcelFiled("核批意见")
    private String approverRemark;
    @ExcelFiled("制单人")
    private String documenterName;
    @ExcelFiled("创造时间")
    private Date createTime;
    @ExcelFiled("修改人")
    private String updateName;//修改人名字
    @ExcelFiled("修改时间")
    private Date updateTime;
    @ExcelFiled("供应商名称")
    private String providerName;//供应商名字
    @ExcelFiled("联系人")
    private String contactperson;
    @ExcelFiled("联系电话")
    private String phone;
    @ExcelFiled("传真")
    private String fax;
    @ExcelFiled("邮件")
    private String email;
    @ExcelFiled("交货日期")
    private Date deliveryDate;
    @ExcelFiled("交货人")
    private String deliveryName;//交货人名字
    @ExcelFiled(value = "药品明细",type = MedicineVO.class)
    private List<MedicineVO> medicineList;
}
