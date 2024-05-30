package com.kgc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import com.kgc.entity.BaseMedicine;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class KcSalefromwareVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelFiled("单据编号")
    private String code;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ExcelFiled("单据日期")
    private Date orderDate;
    @ExcelFiled("源单号")
    private String orderNo;
    @ExcelFiled("总金额")
    private BigDecimal totalPrice;
    @ExcelFiled("总数量")
    private Integer totalNumber;
    @ExcelFiled("备注")
    private String remark;
    @ExcelFiled("创建人")
    private String createByName;
    @ExcelFiled("修改人")
    private String updateByName;
    @ExcelFiled("修改时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateDate;
    @ExcelFiled(value = "药品明细",type = BaseMedicine.class)
    private List<BaseMedicine> baseMedicineList;



}
