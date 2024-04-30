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
public class KcDisfromware implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调度出库id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 调度出库单据
     */
    private String code;

    /**
     * 调度单
     */
    @TableField("dispatchId")
    private Integer dispatchid;

    /**
     * 删除id
     */
    @TableField("isDel")
    private Integer isdel;


}
