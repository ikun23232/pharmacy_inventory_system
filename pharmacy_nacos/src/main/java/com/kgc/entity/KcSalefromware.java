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
public class KcSalefromware implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售出库id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 销售编号单据
     */
    private String code;

    /**
     * 销售订单id
     */
    @TableField("orderId")
    private Integer orderid;


}
