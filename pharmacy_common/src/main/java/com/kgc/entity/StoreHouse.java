package com.kgc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 15279
 * @description 功能描述
 * @create 2024/5/7 8:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StoreHouse implements Serializable {
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
    private Integer userid;

    /**
     * 创建人
     */
    @TableField("createBy")
    private Integer createBy;

    private String createUser;

    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("updateBy")
    private Integer updateBy;

    /**
     * 修改时间
     */
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
}

