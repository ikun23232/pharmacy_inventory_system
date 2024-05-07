package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
      @TableId(value = "logId", type = IdType.AUTO)
    private Integer logid;

    /**
     * 日志内容
     */
    @TableField("logContent")
    private String logcontent;

    /**
     * 操作名称
     */
    private String username;

    /**
     * 操作者的角色
     */
    private Integer role;

    /**
     * 登陆的地址ip
     */
    private String ip;

    /**
     * 创建日期
     */
    @TableField("createDate")
    private LocalDateTime createdate;

    /**
     * 日志类型
     */
    @TableField("logType")
    private Integer logtype;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userid;


}
