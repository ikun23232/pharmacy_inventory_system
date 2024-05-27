package com.kgc.vo;

import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 采购报损Vo
 */
@Data
public class KcReportedVO {
    /**
     * 报损订单id
     */
    @ExcelFiled("报损订单id")
    private Integer id;
    /**
     * 报损编号
     */
    @ExcelFiled("报损编号")
    private String code;
    /**
     * 仓库Id
     */
    private Integer storehouseId;
    /**
     * 仓库名称(外表字段)
     */
    @ExcelFiled("仓库名称")
    private String storehouseName;
    /**
     * 报损类型id
     */
    private Integer reportedTypeId;
    /**
     * 报损类型名称(外表字段)
     */
    @ExcelFiled("报损类型名称")
    private String reportedTypeName;
    /**
     * 审批人
     */
    private int approverBy;
    /**
     * 审批结果：0-未审批 1-未通过 2-通过
     */
    @ExcelFiled("审批结果0未审批1未通过2通过")
    private Integer approvalStatus;
    /**
     * 审批标注
     */
    @ExcelFiled("审批标注")
    private String approverRemark;
    /**
     * 制单人
     */
    private int documenterBy;
    /**
     * 报损时间
     */
    @ExcelFiled("报损时间")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private int createBy;
    /**
     * 修改时间
     */
    @ExcelFiled("修改时间")
    private LocalDateTime modificationTime;
    /**
     * 修改人
     */
    private int modificationBy;
    /**
     * 审批人姓名(外表字段)
     */
    @ExcelFiled("审批人姓名")
    private String approverName;
    /**
     * 制单人姓名(外表字段)
     */
    @ExcelFiled("制单人姓名")
    private String documenterName;
    /**
     * 创建人姓名(外表字段)
     */
    @ExcelFiled("创建人姓名")
    private String createName;
    /**
     * 修改人姓名(外表字段)
     */
    @ExcelFiled("修改人姓名")
    private String modificationName;
    /**
     * 开始时间(模糊查询createTime)
     */
    private String beginTime;
    /**
     * 结束时间(模糊查询createTime)
     */
    private String endTime;

    @ExcelFiled(value = "药品报损明细",type = KcMedicineBSVO.class)
    private List<KcMedicineBSVO> medicineList;

}
