package com.kgc.entity;

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
public class KcMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存-药品主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 仓库编号
     */
    private String code;

    /**
     * 批次编号
     */
    @TableField("batchCode")
    private Integer batchcode;

    /**
     * 药品id
     */
    @TableField("medicineId")
    private Integer medicineid;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 库存价值
     */
    private String money;


}
