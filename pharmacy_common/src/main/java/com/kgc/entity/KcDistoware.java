package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class KcDistoware implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度入库id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 调度入库单据
     */
    private String code;

    /**
     * 调度单id
     */
    @TableField("dispatchId")
    private Integer dispatchid;

    /**
     * 删除id
     */
    @TableField("isDel")
    private Integer isdel;


}
