package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.kgc.annotation.ExcelFiled;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
public class SysLogManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日志内容
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 操作名称
     */
    @TableField("user_name")
    @ExcelFiled("登录账号")
    private String username;

    /**
     * 操作者的角色
     */
    @ExcelFiled("标题")
    private String title;

    /**
     * 登陆的地址ip
     */

    @TableField("operation_ip")
    @ExcelFiled("登陆的地址ip")
    private String ip;

    /**
     * 创建日期
     */
    @TableField("usage_time")
    @ExcelFiled("使用日期")
    private long usageTime;

    /**
     * 日志类型
     */
    @TableField("request_url")
    @ExcelFiled("请求地址")
    private String requestUrl;

    /**
     * 用户id
     */
    @TableField("request_method")
    @ExcelFiled("请求方法")
    private String requestMethod;


    @TableField("request_params")
    @ExcelFiled("请求参数")
    private String requestParams;

    @TableField("operation_time")
    @ExcelFiled("操作时间")
    private Date operationTime;


    @TableField("response_result")
    @ExcelFiled("响应结果")
    private String responseResult;
    @TableLogic
    private String isdel;




}
