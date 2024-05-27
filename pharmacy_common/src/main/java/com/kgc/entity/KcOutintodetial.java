package com.kgc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@ExcelIgnoreUnannotated
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
    @ExcelProperty("明细编号")
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

    /**
     * 药品id
     */
    @TableField("medicineId")
    private Integer medicineId;

    @TableField(exist = false)
    private String medicineName;
    /**
     * 供应商Id
     */
    @TableField("providerId")
    private Integer providerid;

    @TableField(exist = false)
    private String providerName;
    /**
     * 入库金额
     */
    @TableField("toStockMoney")
    private BigDecimal toStockMoney;
    /**
     * 批次编号
     */
    @TableField("batchCode")
    private String batchCode;
    /**
     * 入库数量
     */
    @TableField("toStockQuantity")
    private Integer toStockQuantity;
    /**
     * 出库金额
     */
    @TableField("fromStockMoney")
    private BigDecimal fromStockMoney;
    /**
     * 出库数量
     */
    @TableField("fromStockQuantity")
    private Integer fromStockQuantity;
    /**
     * 单价(当前)
     */
    @TableField("price")
    private BigDecimal price;

    @TableField(exist = false)
    @ExcelProperty("出/入仓库")
    private String warehouseName;
    @TableField(exist = false)
    @ExcelProperty("商品规格")
    private String specification;
    @TableField(exist = false)
    @ExcelProperty("计量名称")
    private String unitName;
    @TableField(exist = false)
    @ExcelProperty("类型")
    private String type;

    @TableField("wareHouseId")
    private Integer wareHouseId;
}
