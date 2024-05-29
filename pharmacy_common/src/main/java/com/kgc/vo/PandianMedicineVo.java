package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;

/**
 * @author daidai
 */
@Data
public class PandianMedicineVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelFiled("仓库名字")
    private String warehouseName;
    @ExcelFiled("药品名称")
    private String medecineName;
    @ExcelFiled("供应商名字")
    private String providername;
    @ExcelFiled("单位名称")
    private String unitName;
    /**
     * 实际存储数量
     */
    @ExcelFiled("实存数量")
    private Integer exactCount;

    @ExcelFiled("总数量")
    private Integer totalQuantity;
    @ExcelFiled("备注")
    private String mark;

}
