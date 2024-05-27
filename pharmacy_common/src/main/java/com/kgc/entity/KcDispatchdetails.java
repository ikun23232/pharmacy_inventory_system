package com.kgc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/20 14:39
 */
@Data
public class KcDispatchdetails implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("aimStoreHouseId")
    private Integer aimStoreHouseId;

    private String aimStoreHouseName;

    @TableField("medicineId")
    private Integer medicineId;

    private String medicineName;

    private String categoryName;

    @TableField("quantity")
    private Integer quantity;

    @TableField(exist = false)
    private BigDecimal purchasePrice;//采购价

    @TableField(exist = false)
    private Integer stock;//剩余库存

    @TableField("price")
    private BigDecimal price;

    @TableField("batchCode")
    private String batchCode;

    @TableField("isDel")
    private Integer isDel;

    @TableField("dispatchCode")
    private String dispatchCode;

    @TableField(exist = false)
    private Integer unitId;

    @TableField(exist = false)
    private String unitName;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    private String specification;

    @TableField("providerId")
    private Integer providerId;

    private String providerName;
}
