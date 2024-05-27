package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志 实体类
 *
 * @author Tellsea
 * @date 2022-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 登录账号
     */
    @TableField("user_name")
    @ExcelFiled("登录账号")
    private String userName;
    /**
     * 操作IP
     */
    @TableField("operation_ip")
    @ExcelFiled("操作IP")
    private String operationIp;
    /**
     * 操作耗时(ms)
     */
    @TableField("usage_time")
    @ExcelFiled("操作耗时")
    private Long usageTime;
    /**
     * 响应结果
     */
    @TableField("response_result")
    @ExcelFiled("响应结果")
    private String responseResult;
    /**
     * 操作时间
     */
    @TableField("operation_time")
    @ExcelFiled("操作时间")
    private Date operationTime;


    @TableLogic
    private Integer isdel;
}
