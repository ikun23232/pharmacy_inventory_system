package com.kgc.entity;


import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.util.Date;
/**
* 
* @TableName kc_alarm
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KcAlarm implements Serializable {

    /**
    * 库存告警id
    */
    private Integer id;
    /**
    * 告警编号
    */
    private String code;
    /**
    * 告警类型 0：本地仓库告警 1：库存总数告警
    */
    private Integer warningType;
    /**
    * 当前告警数量
    */
    private Integer warningCount;
    /**
    * 告警的药品id
    */
    private Integer medicineId;
    /**
    * 是否处理 0：未处理 1:已处理
    */
    private Integer isKnow;
    /**
    * 创造时间
    */
    private Date createDate;
    /**
    * 创造人
    */
    private Integer createBy;
    /**
    * 处理时间
    */
    private Date handleDate;
    /**
    * 处理人
    */
    private Integer handleBy;
    /**
    * 删除id
    */
    @TableLogic
    private Integer isDel;
    /**
    * 是否提醒 0：提醒 1：不提醒
    */
    private Integer isRemind;

    /**
     * 药瓶名称
     */
    private String medicineName;

    /**
     * 最后修改库存姓名
     */
    private String createName;

    /**
     * 处理人姓名
     */
    private String updateName;
}
