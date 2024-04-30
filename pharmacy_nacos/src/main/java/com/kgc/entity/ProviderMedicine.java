package com.kgc.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class ProviderMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商药品表主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商id
     */
    @TableField("providerId")
    private String providerid;

    /**
     * 药品id
     */
    @TableField("medicineId")
    private Integer medicineid;

    /**
     * 价格
     */
    private BigDecimal price;


}
