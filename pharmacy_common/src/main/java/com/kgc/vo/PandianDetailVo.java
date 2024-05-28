package com.kgc.vo;


import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/24 8:32
 */
@Data
public class PandianDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据编号
     */
    @ExcelFiled("商品主键")
    private Integer id;
    /**
     * 单据编号
     */
    @ExcelFiled("盘点编号")
    private String code;

    /**
     * 单据日期
     */
    @ExcelFiled("单据日期")
    private Date billDate;




    @ExcelFiled("盘点人")
    private String checkerByName;





    /**
     * 备注
     */
    @ExcelFiled("备注")
    private String remark;


    /**
     * 审核人
     */
    @ExcelFiled("审核人")
    private String approverByName;

    /**
     * 是否通过
     */
    @ExcelFiled("是否通过(0：未审核 1：未通过 2：通过)")
    private Integer isApproved;

    /**
     * 是否作废
     */
    @ExcelFiled("是否作废(0:未作废 1：作废)")
    private Integer isVoided;

    /**
     * 创建人
     */
    @ExcelFiled("创建人")
    private String createByName;

    /**
     * 创建时间
     */
    @ExcelFiled("创建时间")
    private Date createTime;

    /**
     * 修改人
     */
    @ExcelFiled("修改人")
    private String updateByName;

    /**
     * 修改时间
     */
    @ExcelFiled("修改时间")
    private Date updateTime;

    /**
     * 生效时间
     */
    @ExcelFiled("生效时间")
    private Date effectiveTime;



    /**
     * 审核意见
     */
    @ExcelFiled("审核意见")
    private String approverRemark;

    /**
     * 主题
     */
    @ExcelFiled("主题")
    private String subject;

    @ExcelFiled("仓库名")
    private String warehouseName;


    @ExcelFiled("药品名")
    private String materialCategoryName;

    @ExcelFiled("单据阶段名")
    private String orderStatusName;

    @ExcelFiled(value = "盘点药品明细",type = PandianMedicineVo.class)
    private List<PandianMedicineVo> medicineList;
}