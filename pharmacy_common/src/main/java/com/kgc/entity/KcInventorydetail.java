package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KcInventorydetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存盘点明细
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 仓库id
     */
    private Integer storehouseId;

    /**
     * 药品id
     */
    private Integer medicineId;

    /**
     * 供应商id
     */
    private Integer providerId;

    /**
     * 实际存储数量
     */
    private Integer exactCount;

    /**
     * 盘点信息id
     */
    private Integer inventoryId;

    @TableField(exist = false)
    private String warehouseName;
    @TableField(exist = false)
    private String medecineName;
    @TableField(exist = false)
    private String providername;
    @TableField(exist = false)
    private String unitName;
    @TableField(exist = false)
    private Integer quantity;
}
