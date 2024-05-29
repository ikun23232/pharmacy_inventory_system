package com.kgc.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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

public class KcOutintodetial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 明细id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 明细编号
     */
    @ExcelFiled("明细编号")
    private String code;

    /**
     * 进出库类型
     */
    @TableField("typeId")
    private Integer typeId;

    /**
     * 创造时间
     */
    @TableField("createDate")
    @ExcelFiled("执行时间")
    private Date createDate;

    /**
     * 创造人
     */
    @TableField("createBy")
    private Integer createBy;

    /**
     * 修改时间
     */
    @TableField("updateDate")
    private Date updateDate;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    /**
     * 删除id
     */
    @TableLogic
    @TableField("isDel")
    private String isDel;

    /**
     * 订单编号
     */
    @TableField("orderCode")
    private String orderCode;

    private Integer medicineId;

    @TableField(exist = false)
    @ExcelFiled("药品名称")
    private String medicineName;
    /**
     * 供应商Id
     */
    private Integer providerId;

    @TableField(exist = false)
    @ExcelFiled("供应商")
    private String providerName;
    /**
     * 入库金额
     */
    @ExcelFiled("入库金额")
    private BigDecimal toStockMoney;

    /**
     * 入库数量
     */
    @ExcelFiled("入库数量")
    private Integer toStockQuantity;
    /**
     * 出库金额
     */
    @ExcelFiled("出库金额")
    private BigDecimal fromStockMoney;
    /**
     * 出库数量
     */
    @ExcelFiled("出库数量")
    private Integer fromStockQuantity;
    /**
     * 单价(当前)
     */
    @ExcelFiled("单价")
    private BigDecimal price;
    /**
     * 删除id
    private String isdel;

     * 进出库仓库
     */
    private Integer wareHouseId;

    @TableField(exist = false)
    @ExcelFiled("出入库仓库")
    private String warehouseName;

    @TableField(exist = false)
    @ExcelFiled("产品规格")
    private String specification;

    @TableField(exist = false)
    @ExcelFiled("计量单位")
    private String unitName;

    @TableField(exist = false)
    @ExcelFiled("库存类型")
    private String type;

    @ExcelFiled("批次号")
    private String batchCode;
}
