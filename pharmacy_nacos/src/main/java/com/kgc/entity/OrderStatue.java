package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class OrderStatue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据状态id主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单据状态名称
     */
    private String type;


}
