package com.kgc.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* 
* @TableName kc_dispatchdetails
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class KcDispatchdetails implements Serializable {

    /**
    * 调度详情id
    */

    private Integer id;
    /**
    * 调度单编号
    */

    private String dispatchcode;
    /**
    * 调度的目标仓库
    */

    private Integer aimstorehouseid;
    /**
    * 调度的药品id
    */

    private Integer medicineid;

    @TableField(exist = false)
    private String medicineName;
    /**
    * 调度的数量
    */

    private Integer quantity;
    /**
    * 调度药品的进价
    */
    private BigDecimal price;
    /**
    * 批次号
    */
    private String batchcode;
    /**
    * 删除id
    */
    private Integer isdel;




}
