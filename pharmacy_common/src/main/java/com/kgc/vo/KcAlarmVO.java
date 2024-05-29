package com.kgc.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/24 13:47
 */
@Data
public class KcAlarmVO implements Serializable {
    @ExcelFiled("告警编号")
    private String code;
    @ExcelFiled("告警类型")
    private String warningTypeName;
    @ExcelFiled("当前告警数量")
    private Integer warningCount;
    @ExcelFiled("是否处理")
    private String isKnowName;
    @ExcelFiled("创造时间")
    private Date createDate;
    @ExcelFiled("处理时间")
    private Date handleDate;
    @ExcelFiled("是否提醒")
    private String isRemindName;
    @ExcelFiled("药品名称")
    private String medicineName;
    @ExcelFiled("创造人")
    private String createName;
    @ExcelFiled("处理人")
    private String updateName;
}
