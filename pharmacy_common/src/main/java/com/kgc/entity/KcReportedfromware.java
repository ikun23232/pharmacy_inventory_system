package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
public class KcReportedfromware implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报损出库id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 报损出库编号
     */
    private String code;

    /**
     * 报损id
     */
    @TableField("reportedId")
    private Integer reportedId;

    /**
     * 删除id
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 报损编号
     */
    @TableField("reportedCode")
    private String reportedCode;
    /**
     * 仓库id
     */
    @TableField("storehouseId")
    private Integer storehouseId;
    /**
     * 仓库名称
     */
    @TableField("storehouseName")
    private String storehouseName;

    /**
     * 报损类型id
     */
    @TableField("reportedTypeId")
    private Integer reportedTypeId;
    /**
     * 报损类型
     */
    @TableField("reportedTypeName")
    private String reportedTypeName;

    /**
     * 出库时间
     */
    @TableField("modificationBy")
    private LocalDateTime modificationTime;

    /**
     * 出库人
     */
    @TableField("modificationName")
    private String modificationName;

}
