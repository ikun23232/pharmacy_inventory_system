package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
<<<<<<<< HEAD:pharmacy_nacos/src/main/java/com/kgc/entity/BaseMedicine.java
import java.io.Serializable;
import java.util.Date;

========
import com.baomidou.mybatisplus.annotation.TableId;
>>>>>>>> 6cd3204fa3bdca2294d361faa6e95f9112c9e316:pharmacy_common/src/main/java/com/kgc/entity/BaseMedicine.java
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
public class BaseMedicine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医用商品名称
     */
    private String name;

    /**
     * 医用商品类型
     */
    private Integer type;

    /**
     * 计量单位
     */
    @TableField("measureId")
    private Integer measureid;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private Integer isdel;

    /**
     * 零售价
     */
    private Double sale;

    /**
     * 医用商品规格
     */
    private String specification;

    /**
     * 库存预警值
     */
    private Integer warning;


}
