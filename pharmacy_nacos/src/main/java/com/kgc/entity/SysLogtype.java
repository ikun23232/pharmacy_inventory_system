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
public class SysLogtype implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志类型id
     */
      @TableId(value = "logId", type = IdType.AUTO)
    private Integer logid;

    /**
     * 日志类型名字
     */
    @TableField("logTypeName")
    private String logtypename;


}
