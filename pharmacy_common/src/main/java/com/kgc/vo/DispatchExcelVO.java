package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.annotation.ExcelFiled;
import com.kgc.entity.BaseMedicine;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/27 15:42
 */
@Data
public class DispatchExcelVO implements Serializable {
    private Integer id;
    /**
     * 调度表编号
     */
    @ExcelFiled("调度表编号")
    private String code;

    /**
     * 调度主题
     */
    @ExcelFiled("调度主题")
    private String subject;

    /**
     * 调度的总价格
     */
    @ExcelFiled("调度的总价格")
    private BigDecimal price;

    /**
     * 原仓库Id
     */
    @ExcelFiled("原仓库")
    private String beforeWareName;

    /**
     * 备注
     */
    @ExcelFiled("备注")
    private String remark;

    /**
     * 审批人
     */
    @ExcelFiled("审批人")
    private String approverName;

    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @ExcelFiled("审批结果")
    private String approvalStatus;

    /**
     * 核批备注
     */
    @ExcelFiled("核批备注")
    private String approverRemark;


    /**
     * 创造时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelFiled("创造时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    @ExcelFiled("创造人")
    private String createName;

    /**
     * 修改时间
     */
    @ExcelFiled("修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


    @ExcelFiled("修改人")
    private String updateName;


    @ExcelFiled("单据状态")
    private String orderStatusResult;


    @ExcelFiled(value = "药品明细",type = DispatchMedicineVO.class)
    private List<DispatchMedicineVO> medicineList;


    @ExcelFiled("调度时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dispatchTime;//调度时间

    @ExcelFiled("调度总数")
    private Integer totalCount;
}
