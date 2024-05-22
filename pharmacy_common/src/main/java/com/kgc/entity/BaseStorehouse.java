package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class BaseStorehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库地址
     */
    private String address;

    /**
     * 仓库容量
     */
    private String capacity;

    /**
     * 负责人
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    /**
     * 修改时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 仓库编号
     */
    private String code;

    @TableField(exist=false)
    private String createUser;

    @TableField(exist=false)
    private String updateUser;
}