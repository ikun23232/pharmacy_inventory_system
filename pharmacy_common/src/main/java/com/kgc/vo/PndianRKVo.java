package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author daidai
 */
@Data
public class PndianRKVo implements Serializable {
    private static final long serialVersionUID = 1L;



    /**
     * 盘点入库编号
     */
    @ExcelFiled("盘点入库编号")
    private String inventoryCode;

    /**
     * 入库数量
     */
    @ExcelFiled("入库数量")
    private Integer rkQuantity;

    @ExcelFiled("源单号")
    private String code;
    @ExcelFiled("药品名称")
    private String materialCategoryName;
    @ExcelFiled("仓库名称")
    private String storehouseName;
    @ExcelFiled("供应商名称")
    private String providerName;
    @ExcelFiled("创建时间")
    private Date createDate;

}
