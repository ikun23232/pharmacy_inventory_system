package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class KcInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 盘点id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 盘点日期
     */
    private LocalDateTime createdate;

    /**
     * 盘点人员
     */
    private Integer createby;

    /**
     * 仓库编号
     */
    private String code;

    /**
     * 药品id
     */
    private Integer medicineid;

    /**
     * 药品名称
     */
    private String medicinename;

    /**
     * 系统库存数量
     */
    private Integer originalcount;

    /**
     * 实际库存数量
     */
    private Integer nowcount;

    /**
     * 盘点结果 0：相符 1：盘亏 2 ：盘盈
     */
    private Integer result;

    /**
     * 盘点备注
     */
    private String remark;


}
